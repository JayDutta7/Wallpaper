package app.matrix.wallpaperpexels.login.contract

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ContractLoginInterface {

    interface View{

        //configure xml id
        fun init()

        //onStart
        fun isalrdylogin()

        //FirebaseAuth
        fun firebaseAuthWithGoogle(acct: GoogleSignInAccount)

        //configure google sign in
        fun configureGoogleSignIn()

        //clickied register text
        fun clickToRegistrationPage()

        //clicked google sign in button
        fun clickgoogleSignIn()

        //clicked signin button

        fun clickLogin()


        fun uselocaldatabase()


    }

    interface Model{


    }

    interface Presenter{
        fun configureGoogle()

        fun clickedregistration()

        fun googlesigninclicked()

        fun loginbuttonclicked()

        fun uselocaldatabase()

    }





}