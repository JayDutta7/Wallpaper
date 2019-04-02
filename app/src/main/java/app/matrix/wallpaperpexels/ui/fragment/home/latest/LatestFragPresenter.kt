package app.matrix.wallpaperpexels.ui.fragment.home.latest


import app.matrix.wallpaperpexels.network.Response
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import app.matrix.wallpaperpexels.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LatestFragPresenter<V:LatestFragMvp.iLatestFragView>(private val repository: LatestFragMvp.iLatestFragRepository):
    BasePresenter<V>(),LatestFragMvp.iLatestFragPresenter<V> {


    /*MVPPresenter(=)*/
    override  fun fetchRandomApi() {
        GlobalScope.launch(Dispatchers.Main) {

            val res = RetroClass.getClient.getDetails().await()

            if(res.isSuccessful){

            Response.Success(res)

            }else{

               // Response.Error(LocalDa)

            }


        }
    }

    /*Base(=)Presenter*/
    override fun onDestroy() {
        onDetach()
    }












}