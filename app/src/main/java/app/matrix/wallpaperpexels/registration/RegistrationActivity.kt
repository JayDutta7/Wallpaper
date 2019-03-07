package app.matrix.wallpaperpexels.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.localdatabase.DatabaseHelper
import app.matrix.wallpaperpexels.localdatabase.pojo.UserDetailsData
import app.matrix.wallpaperpexels.login.LoginActivity
import app.matrix.wallpaperpexels.utility.InputValidation
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {


    @BindView(R.id.textInputEditTextName)
    lateinit var EditTextName: TextInputEditText

    @BindView(R.id.textInputEditTextEmail)
    lateinit var textInputEditTextEmail: TextInputEditText

    @BindView(R.id.textInputEditTextPassword)
    lateinit var textInputEditTextPassword: TextInputEditText

    @BindView(R.id.textInputEditTextConfirmPassword)
    lateinit var textInputEditTextConfirmPassword: TextInputEditText

    @BindView(R.id.textInputEditTextMobile)
    lateinit var textInputEditTextMobile: TextInputEditText

    @BindView(R.id.ButtonRegister)
    lateinit var ButtonRegister: AppCompatButton

    @BindView(R.id.TextViewLoginLink)
    lateinit var TextViewLoginLink: AppCompatTextView


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

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initialize()

    }//end of oncreate

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.TextViewLoginLink -> goToLogin()

            R.id.ButtonRegister -> registerUsingSqlLite()


        }
    }

    private fun initialize() {
        ButterKnife.bind(this)

        //Init Object
        initObject()

        //Initialization
        TextViewLoginLink.setOnClickListener(this)
        ButtonRegister.setOnClickListener(this)

    }

    private fun initObject() {
        inputValidation = InputValidation(this@RegistrationActivity)
        databaseHelper = DatabaseHelper(this@RegistrationActivity)

    }

    private fun goToLogin() {
        val mainIntent = Intent(this@RegistrationActivity, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun registerUsingSqlLite() {
        if (!inputValidation.isInputEditTextFilled(
                EditTextName,
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
                    name = EditTextName.text.toString().trim(),
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
