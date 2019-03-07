package app.matrix.wallpaperpexels.network

import app.matrix.wallpaperpexels.home.pojo.Random
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.http.Multipart
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface ApiInterface {

    @GET("curated?per_page=10&page=1")
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