package app.matrix.wallpaperpexels.network

import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiInterface {




    @GET("curated?per_page=20&page=1")
    @Headers(
        "Authorization:" + Constant.apiKey,
        "Content-type:application/json"
    )
    fun getDetails(): Call<Random>


    object CreateRetrofit {
        val apiService: ApiInterface
            get() = RetroClass.getClient(Constant.baseUrl)!!.create(ApiInterface::class.java)
    }





}