package app.matrix.wallpaperpexels.aftersplash.presenter

import app.matrix.wallpaperpexels.aftersplash.contract.AfterSplashInterface

class AfterSplashPresenter(_view:AfterSplashInterface.View):AfterSplashInterface.Presenter {
    override fun clickLogin() {
        view.loginClicked()
    }

    override fun clickRegistration() {
       view.registrationClicked()
    }


    private var view: AfterSplashInterface.View = _view

    init {
        view.init()
    }


}