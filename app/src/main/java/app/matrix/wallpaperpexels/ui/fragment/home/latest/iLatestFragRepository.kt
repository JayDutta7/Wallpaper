package app.matrix.wallpaperpexels.ui.fragment.home.latest

import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Photos

interface iLatestFragRepository {

    fun showLatestDataResponse(onresponse:ResponseListener)



    interface ResponseListener{
        fun onSuccess(dataList: MutableList<Photos>)
        fun onFailure(t: Throwable)
    }

}