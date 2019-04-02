package app.matrix.wallpaperpexels.network

import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {



    @GET(Constant.randomPhoto)
    @Headers(
        "Authorization:" + Constant.apiKey,
        "Content-type:application/json"
    )
   fun getDetails(): Deferred<Response<Random>>


    //unknown amount of parameters to pass in GET---@FieldMap Map<String, String> params
    fun SearchAndCategoryDetails(@Query("search?query=")value:String?):Deferred<Random>







}