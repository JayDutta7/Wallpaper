package app.matrix.wallpaperpexels.ui.fragment.home.latest


import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.ui.base.iBasePresenter
import app.matrix.wallpaperpexels.ui.base.iBaseView
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

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





    }


}