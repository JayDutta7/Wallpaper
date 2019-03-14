package app.matrix.wallpaperpexels.ui.activity.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface IloginView {

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

    //skip login
    fun skipLogin()

}