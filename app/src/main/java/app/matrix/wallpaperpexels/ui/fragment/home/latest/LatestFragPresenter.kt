package app.matrix.wallpaperpexels.ui.fragment.home.latest


import app.matrix.wallpaperpexels.ui.base.BasePresenter


class LatestFragPresenter<V:LatestFragMvp.iLatestFragView>(private val repository: LatestFragMvp.iLatestFragRepository):
    BasePresenter<V>(),LatestFragMvp.iLatestFragPresenter<V> {


    /*MVPPresenter(=)*/
    override fun fetchRandomApi() {

    }

    /*Base(=)Presenter*/
    override fun onDestroy() {
        onDetach()
    }












}