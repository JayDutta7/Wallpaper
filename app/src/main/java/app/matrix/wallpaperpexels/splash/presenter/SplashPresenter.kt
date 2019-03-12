package app.matrix.wallpaperpexels.splash.presenter

import app.matrix.wallpaperpexels.splash.contract.ContractSplashInterface

class SplashPresenter(_view: ContractSplashInterface.View) : ContractSplashInterface.Presenter {


    override fun usePreferrence() {
        view.usePreference()
    }

    private var view: ContractSplashInterface.View = _view


    init {

        view.moveToNextPage()

    }


    override fun goToNextPage() {
        view.moveToNextPage()
    }

}