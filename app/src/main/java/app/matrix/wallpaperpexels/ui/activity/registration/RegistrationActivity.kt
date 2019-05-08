package app.matrix.wallpaperpexels.ui.activity.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.database.StaticKT.Constant
import app.matrix.wallpaperpexels.database.DatabaseHelper
import app.matrix.wallpaperpexels.database.rooMDb.model.UserDetailsData
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import app.matrix.wallpaperpexels.utility.InputValidation
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistrationActivity : AppCompatActivity(), IRegistrationView {


    @BindView(R.id.textInputEditTextName)
    lateinit var textInputEditTextName: TextInputEditText

    @BindView(R.id.textInputEditTextEmail)
    lateinit var textInputEditTextEmail: TextInputEditText

    @BindView(R.id.textInputEditTextPassword)
    lateinit var textInputEditTextPassword: TextInputEditText

    @BindView(R.id.textInputEditTextConfirmPassword)
    lateinit var textInputEditTextConfirmPassword: TextInputEditText

    @BindView(R.id.textInputEditTextMobile)
    lateinit var textInputEditTextMobile: TextInputEditText

    /*@BindView(R.id.ButtonRegister)
    lateinit var ButtonRegister: AppCompatButton

    @BindView(R.id.TextViewLoginLink)
    lateinit var TextViewLoginLink: AppCompatTextView*/


    @BindView(R.id.textInputLayoutName)
    lateinit var textInputLayoutName: TextInputLayout

    @BindView(R.id.textInputLayoutEmail)
    lateinit var textInputLayoutEmail: TextInputLayout

    @BindView(R.id.textInputLayoutPassword)
    lateinit var textInputLayoutPassword: TextInputLayout

    @BindView(R.id.textInputLayoutConfirmPassword)
    lateinit var textInputLayoutConfirmPassword: TextInputLayout

    @BindView(R.id.textInputLayoutmobile)
    lateinit var textInputLayoutmobile: TextInputLayout

    @BindView(R.id.nestedScrollView)
    lateinit var nestedScrollView: NestedScrollView

    @BindView(R.id.root_layout)
    lateinit var root_layout:ConstraintLayout


    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    private var registrationPresenter: RegistrationPresenter? = null

    companion object {
        private val TAG = RegistrationActivity::javaClass.name
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        ButterKnife.bind(this)

        //Init Object
        inputValidation = InputValidation(this@RegistrationActivity)
        databaseHelper = DatabaseHelper(this@RegistrationActivity)

        //Initiating presenter
        registrationPresenter = RegistrationPresenter(this)


    }//end of oncreate


    //Destroy and
    override fun onDestroy() {
        super.onDestroy()
        WallPaperApp.getPref().save(Constant.RegType, "")
    }

    //System Backpressed Destroy the activity
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    @OnClick
        (R.id.TextViewLoginLink)
    fun goToLogin() {
        registrationPresenter?.redirectLogin()

    }


    @OnClick(R.id.ButtonRegister)
    fun registerUsingSqlLite() {
        // registrationPresenter?.registerSqllite()
        registrationPresenter?.typeRegistration()

    }

    @OnClick(R.id.firebase)
    fun firebaseRadio() {
        //Toast.makeText(this, "Clicked Firebase.. Cooming Soon", Toast.LENGTH_SHORT).show()

        WallPaperApp.getPref().save(Constant.RegType, Constant.Firebase)


    }

    @OnClick(R.id.sqllite)
    fun registerSqlLite() {
        //Toast.makeText(this, "Clicked Sqllite", Toast.LENGTH_SHORT).show()
        WallPaperApp.getPref().save(Constant.RegType, Constant.Sqllite)

    }

    override fun registerFirebase() {

        val mauth = FirebaseAuth.getInstance()
        when {
            !inputValidation.isInputEditTextFilled(
                textInputEditTextName,
                textInputLayoutName,
                getString(R.string.error_message_name)
            )
            -> return
            !inputValidation.isInputEditTextFilled(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_message_email)
            )
            -> return
            !inputValidation.isInputEditTextEmail(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_valid_email_password)
            )
            -> return
            !inputValidation.isInputEditTextFilled(
                textInputEditTextPassword,
                textInputLayoutPassword,
                getString(R.string.error_message_password)
            )
            -> return
            !inputValidation.isInputEditTextFilled(
                textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword,
                getString(R.string.error_message_password)
            )
            -> return
            !inputValidation.isInputEditTextMatches(
                textInputEditTextPassword,
                textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword,
                getString(R.string.error_password_match)
            )
            -> return
            !inputValidation.isPhoneNumberValid(
                textInputEditTextMobile,
                textInputLayoutmobile,
                getString(R.string.hint_mobile)
            )
            -> return

            else -> mauth.createUserWithEmailAndPassword(
                textInputEditTextEmail.text.toString().trim(),//Email
                textInputEditTextPassword.text.toString().trim()//Password
            ).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val userInfo = task.result?.user
                    Log.e(TAG, userInfo.toString())

                    if (userInfo != null) {

                        val database = FirebaseDatabase.getInstance().getReference("users")
                        database.child(userInfo.uid)


                        val firebaseModel = RegFirebaseModel(

                            fullname = textInputEditTextName.text.toString().trim(),
                            email = textInputEditTextEmail.text.toString().trim(),
                            phone = textInputEditTextMobile.text.toString().trim()
                        )
                        database.child(userInfo.uid).setValue(firebaseModel)



                        database.child(userInfo.uid).addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                Toast.makeText(this@RegistrationActivity, "Registration Failed.", Toast.LENGTH_LONG)
                                    .show()

                            }

                            override fun onDataChange(p0: DataSnapshot) {

                                val user = FirebaseAuth.getInstance().currentUser
                                if (user != null) {

                                    Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_SHORT).show()
                                    registrationPresenter?.redirectLogin()

                                }
                            }

                        })
                    }


                } else {



                    Snackbar.make(root_layout,getString(R.string.error_message_gmail),Snackbar.LENGTH_SHORT)

                }


            }

        }


    }


    override fun redirectLogin() {
        val mainIntent = Intent(this@RegistrationActivity, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun showErrorToast() {

        Toast.makeText(this, "Please choose registration type", Toast.LENGTH_SHORT).show()

    }

    override fun registerSqllite() {

        if (!inputValidation.isInputEditTextFilled(
                textInputEditTextName,
                textInputLayoutName,
                getString(R.string.error_message_name)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextFilled(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_message_email)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextEmail(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_valid_email_password)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextFilled(
                textInputEditTextPassword,
                textInputLayoutPassword,
                getString(R.string.error_message_password)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextFilled(
                textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword,
                getString(R.string.error_message_password)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextMatches(
                textInputEditTextPassword,
                textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword,
                getString(R.string.error_password_match)
            )
        ) {
            return

        } else if (!inputValidation.isPhoneNumberValid(
                textInputEditTextMobile,
                textInputLayoutmobile,
                getString(R.string.hint_mobile)
            )
        ) {
            return

        } else {


            if (!databaseHelper.checkUserEmail(textInputEditTextEmail.text.toString().trim())) {

                val user = UserDetailsData(
                    name = textInputEditTextName.text.toString().trim(),
                    email = textInputEditTextEmail.text.toString().trim(),
                    password = textInputEditTextPassword.text.toString().trim(),
                    phonenumber = textInputEditTextMobile.text.toString().trim()
                )

                databaseHelper.addUser(user)
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_SHORT).show()
                //Go to login page
                //goToLogin()
            } else {
                Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_SHORT).show()
            }


        }
    }


}
