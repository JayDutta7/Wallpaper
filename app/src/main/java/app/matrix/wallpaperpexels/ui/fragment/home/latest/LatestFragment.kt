package app.matrix.wallpaperpexels.ui.fragment.home.latest


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.network.ApiInterface
import app.matrix.wallpaperpexels.ui.activity.imagedetails.ImageDetails
import app.matrix.wallpaperpexels.ui.fragment.home.adapter.latestPhoto.PhotoAdapter
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Photos
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Random
import butterknife.BindView
import butterknife.ButterKnife
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 *
 */


class LatestFragment : Fragment(), ClickedItem, iLatestFragView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.swipeRefresh)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private var imgList: MutableList<MutableList<Photos>>? = null

    private var mAPIService: ApiInterface? = null


    private val TAG: String = LatestFragment::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ButterKnife Binding
        ButterKnife.bind(this, view)

        //initialize retrofit
        mAPIService = ApiInterface.CreateRetrofit.apiService

        //intiate arraylist
        imgList = ArrayList()

        //RecyclerView Binding
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)

        showData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_latest, container, false)
    }

    override fun initView() {



    }

    private fun showData() {

        swipeRefresh.isRefreshing = true

        mAPIService!!.getDetails().enqueue(object : retrofit2.Callback<Random> {

            override fun onFailure(call: Call<Random>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Log.e(TAG, "Failure" + t.message)

            }

            override fun onResponse(call: Call<Random>, response: Response<Random>) {

                Log.e(TAG, "Sucess" + response.body()?.photos?.size!!)
                swipeRefresh.isRefreshing = false
                when {
                    !imgList.isNullOrEmpty() -> imgList!!.clear()
                    response.body()?.photos?.size!! > 0 -> imgList!!.add(response.body()!!.photos!!)
                    else -> Log.e(TAG, "API NO_RESAPONSE")
                }


                when {
                    response.body()?.photos?.size!! > 0 ->

                        recyclerView.adapter =
                            PhotoAdapter(
                                activity!!,
                                response.body()!!.photos!!,
                                this@LatestFragment
                            )

                    else -> Log.e(TAG, "Some Error Occured")
                }
            }

        })
    }


    fun calculateNoOfColumns( columnWidthDp: Float): Int { // For example columnWidthdp=180
        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }


    override fun clickpostion(Position: Int, Msg: String) {
        when (Msg) {
            "Details" -> for (i in 0 until imgList!!.size) {
                //Move
                val mainIntent = Intent(activity, ImageDetails::class.java)
                mainIntent.putExtra("link", imgList?.get(i)?.get(Position)?.src?.portrait)
                startActivity(mainIntent)
            }
            else -> {
                    Toast.makeText(context,"Added To Favorites",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onRefresh() {
        when {
            swipeRefresh.isRefreshing -> {
                swipeRefresh.isRefreshing = true
            }

        }

    }


}
