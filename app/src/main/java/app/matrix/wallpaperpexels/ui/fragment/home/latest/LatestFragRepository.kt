package app.matrix.wallpaperpexels.ui.fragment.home.latest

import app.matrix.wallpaperpexels.network.ApiInterface
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import retrofit2.Call
import retrofit2.Response

class LatestFragRepository:iLatestFragRepository {


    override fun showLatestDataResponse(onresponse: iLatestFragRepository.ResponseListener) {

        ApiInterface.CreateRetrofit.apiService.getDetails().enqueue(object :retrofit2.Callback<Random>{
            override fun onFailure(call: Call<Random>, t: Throwable) {

                onresponse.onFailure(t)

            }

            override fun onResponse(call: Call<Random>, response: Response<Random>) {

            onresponse.onSuccess(response.body()!!.photos!!)

            }

        })

    }


}