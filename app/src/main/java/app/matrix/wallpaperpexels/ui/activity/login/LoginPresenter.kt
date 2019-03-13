package app.matrix.wallpaperpexels.ui.activity.login

import app.matrix.wallpaperpexels.ui.activity.login.contract.ContractLoginInterface

class LoginPresenter(_view: ContractLoginInterface.View) : ContractLoginInterface.Presenter {

    private var loginview: ContractLoginInterface.View = _view
    init {
        loginview.init()
    }
    override fun configureGoogle() {
        loginview.configureGoogleSignIn()
    }
    override fun loginbuttonclicked() {
        loginview.clickLogin()
    }
    override fun googlesigninclicked() {
        loginview.clickgoogleSignIn()
    }
    override fun clickedregistration() {
        loginview.clickToRegistrationPage()
    }
}