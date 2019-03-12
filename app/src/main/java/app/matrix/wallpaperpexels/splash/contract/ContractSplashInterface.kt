package app.matrix.wallpaperpexels.splash.contract

interface ContractSplashInterface {

    interface View{
       // fun initView()
        fun moveToNextPage()

        fun usePreference()
    }

    interface Presenter{
        fun goToNextPage()

        fun usePreferrence()


    }

    interface Model{

    }

}