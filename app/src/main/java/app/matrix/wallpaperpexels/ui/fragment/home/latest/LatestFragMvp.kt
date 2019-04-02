package app.matrix.wallpaperpexels.ui.fragment.home.latest


import app.matrix.wallpaperpexels.ui.base.iBasePresenter
import app.matrix.wallpaperpexels.ui.base.iBaseView


interface LatestFragMvp {

    ////View
    interface iLatestFragView:iBaseView{
         fun fetchRandomApi()
    }

    /////Presenter
    interface iLatestFragPresenter<V:iBaseView>:iBasePresenter<V>{
        fun fetchRandomApi()

    }

    /////Repository
    interface iLatestFragRepository{

         fun fetchRandomApi()


    }


}