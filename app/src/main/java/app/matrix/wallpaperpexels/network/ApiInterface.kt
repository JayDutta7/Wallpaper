package app.matrix.wallpaperpexels.network

import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {



    @GET("curated?per_page=100&page=1")
    @Headers(
        "Authorization:" + Constant.apiKey,
        "Content-type:application/json"
    )
    fun getDetails(): Call<Random>
    /*With Rx*/
    //fun getDetails(): Observable<Random>


    /*unknown amount of parameters to pass in GET---@FieldMap Map<String, String> params*/
    fun SearchAndCategoryDetails(@Query("search?query=")value:String?):Call<Random>




    object CreateRetrofit {
        val apiService: ApiInterface
            get() = RetroClass.getClient(Constant.PexelsbaseUrl)!!.create(ApiInterface::class.java)
    }





}