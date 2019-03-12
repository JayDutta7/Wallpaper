package app.matrix.wallpaperpexels.login.presenter

import app.matrix.wallpaperpexels.login.contract.ContractLoginInterface

class LoginPresenter(_view: ContractLoginInterface.View) : ContractLoginInterface.Presenter {


    override fun uselocaldatabase() {
        loginview.uselocaldatabase()
    }


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