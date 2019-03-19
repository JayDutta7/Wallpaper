package app.matrix.wallpaperpexels.ui.activity.login

class LoginPresenter(private var loginview: IloginView) : IloginPreseneter {
    override fun skipLogin() {
        loginview.skipLogin()
    }

    override fun configureGoogle() {
        loginview.configureGoogleSignIn()
    }

    override fun clickedregistration() {
        loginview.clickToRegistrationPage()
    }

    override fun googlesigninclicked() {
        loginview.clickgoogleSignIn()
    }

    override fun loginbuttonclicked() {
        loginview.clickLogin()
    }
}


