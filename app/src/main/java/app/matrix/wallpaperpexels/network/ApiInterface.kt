package app.matrix.wallpaperpexels.network

import app.matrix.wallpaperpexels.database.StaticKT.Constant
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiInterface {

    @GET(Constant.randomPhoto)
    @Headers(
        "Authorization:" + Constant.apiKey,
        "Content-type:application/json"
    )
            /*Response--Retrofit2 default response class--[[[Sealed Class]]]-Response*/
    fun getDetails(): Deferred<Response<Random>>//Corotine added intial

    @GET(Constant.getAPhoto)
    @Headers(
        "Authorization:" + Constant.apiKey,
        "Content-type:application/json"
    )
    fun getPhotoDetails(photoid: String?)


    //unknown amount of parameters to pass in GET---@FieldMap Map<String, String> params
  // fun SearchAndCategoryDetails(@Query() value: String?): Deferred<Random>


}