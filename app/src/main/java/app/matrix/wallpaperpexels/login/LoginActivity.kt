package app.matrix.wallpaperpexels.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.home.Home
import app.matrix.wallpaperpexels.localdatabase.DatabaseHelper
import app.matrix.wallpaperpexels.localdatabase.pojo.UserDetailsData
import app.matrix.wallpaperpexels.login.contract.ContractLoginInterface
import app.matrix.wallpaperpexels.login.presenter.LoginPresenter
import app.matrix.wallpaperpexels.registration.RegistrationActivity
import app.matrix.wallpaperpexels.utility.InputValidation
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity(), View.OnClickListener, ContractLoginInterface.View {


    @BindView(R.id.textInputEditTextEmail)
    lateinit var emailEditText: TextInputEditText

    @BindView(R.id.textInputLayoutEmail)
    lateinit var textInputLayoutEmail: TextInputLayout

    @BindView(R.id.textInputLayoutPassword)
    lateinit var textInputLayoutPassword: TextInputLayout

    @BindView(R.id.textInputEditTextPassword)
    lateinit var passEditText: TextInputEditText

    @BindView(R.id.appCompatButtonLogin)
    lateinit var loginButton: AppCompatButton

    @BindView(R.id.textViewLinkRegister)
    lateinit var textViewClick: AppCompatTextView

    @BindView(R.id.nestedScrollView)
    lateinit var nestedScrollView: NestedScrollView

    @BindView(R.id.textViewForgotPassword)
    lateinit var textViewForgotPassword: AppCompatTextView

    @BindView(R.id.sign_in_button)
    lateinit var googleSignIn: SignInButton

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var listUsers: MutableList<UserDetailsData>

    private val TAG = LoginActivity::class.java.name

    //Google Signin
    private val RC_SIGN_IN: Int = 1
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions

    private lateinit var firebaseAuth: FirebaseAuth

    private var presenter: LoginPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)


        /* var getDataFromSQLite = GetDataFromSQLite()
         getDataFromSQLite.execute()*/

    }

    override fun init() {
        ButterKnife.bind(this)

        //Initialization new class
        inputValidation = InputValidation(this@LoginActivity)
        databaseHelper = DatabaseHelper(this@LoginActivity)

        firebaseAuth = FirebaseAuth.getInstance()


        listUsers = ArrayList()
        //set SignIn
        googleSignIn.setSize(SignInButton.SIZE_STANDARD)

        //Configure Google Signin
        configureGoogleSignIn()

        //Initialize onClick
        loginButton.setOnClickListener(this)
        textViewClick.setOnClickListener(this)
        textViewForgotPassword.setOnClickListener(this)
        googleSignIn.setOnClickListener(this)
    }


    override fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("741256386904-8cv1alu7qvflus1mhtitejqn2g6qs2mi.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }


    override fun isalrdylogin() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val mainIntent = Intent(this@LoginActivity, Home::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(mainIntent)
        }
    }

    /*Android super method*/
    override fun onStart() {
        super.onStart()
        isalrdylogin()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: Exception) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                Log.d(TAG,"Name" + acct.displayName + "Email" + acct.email + "Image" +acct.photoUrl)


                val mainIntent = Intent(this@LoginActivity, Home::class.java)
                mainIntent.putExtra("email",acct.email)
                mainIntent.putExtra("Name",acct.displayName)
                mainIntent.putExtra("image",acct.photoUrl)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(mainIntent)
            } else {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    /*To Get All Data from Database Sqlite*/

    /*inner class GetDataFromSQLite : AsyncTask<Void, Void, List<UserDetailsData>>() {

        override fun doInBackground(vararg p0: Void?): List<UserDetailsData> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<UserDetailsData>?) {
            super.onPostExecute(result)
            Log.e(TAG, "" + result?.size)
            listUsers.clear()
            listUsers.addAll(result!!)
        }

    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textViewLinkRegister -> presenter?.clickedregistration()

            R.id.appCompatButtonLogin -> presenter?.loginbuttonclicked()

            R.id.sign_in_button -> presenter?.googlesigninclicked()

        }
    }

    override fun clickToRegistrationPage() {

        val mainIntent = Intent(this@LoginActivity, RegistrationActivity::class.java)
        startActivity(mainIntent)

    }

    override fun clickgoogleSignIn() {

        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun clickLogin() {

        if (!inputValidation.isInputEditTextFilled(
                emailEditText,
                textInputLayoutEmail,
                getString(R.string.error_message_email)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextEmail(
                emailEditText,
                textInputLayoutEmail,
                getString(R.string.error_valid_email_password)
            )
        ) {
            return
        } else if (!inputValidation.isInputEditTextFilled(
                passEditText,
                textInputLayoutPassword,
                getString(R.string.error_message_password)
            )
        ) {
            return
        } else {

            if (databaseHelper.checkUserExits(
                    emailEditText.text.toString().trim { it <= ' ' },
                    passEditText.text.toString().trim { it <= ' ' })
            ) {
                Snackbar.make(nestedScrollView, getString(R.string.success_Loginmessage), Snackbar.LENGTH_SHORT).show()

                val mainIntent = Intent(this@LoginActivity, Home::class.java)
                startActivity(mainIntent)
                finish()

            } else {

                Snackbar.make(nestedScrollView, getString(R.string.user_notexits), Snackbar.LENGTH_SHORT).show()
            }


        }

    }


}
