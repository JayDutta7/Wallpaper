package app.matrix.wallpaperpexels.aftersplash.contract

interface AfterSplashInterface {

    interface View {
        fun init()

        fun loginClicked()
        fun registrationClicked()
    }

    interface Presenter {
        fun clickLogin()
        fun clickRegistration()
    }

    interface Model {

    }
}