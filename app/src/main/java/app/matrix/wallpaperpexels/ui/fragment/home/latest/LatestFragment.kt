package app.matrix.wallpaperpexels.ui.fragment.home.latest


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.network.ApiInterface
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import app.matrix.wallpaperpexels.ui.activity.imagedetails.ImageDetails
import app.matrix.wallpaperpexels.ui.base.BaseFragment
import app.matrix.wallpaperpexels.ui.fragment.home.adapter.latestPhoto.PhotoAdapter
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Photos
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 *
 */


class LatestFragment : BaseFragment(), ClickedItem, LatestFragMvp.iLatestFragView,
    SwipeRefreshLayout.OnRefreshListener {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_latest
    }

    override fun fetchRandomApi() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.swipeRefresh)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private var imgList: MutableList<MutableList<Photos>>? = null




    private val TAG: String = LatestFragment::class.java.simpleName


    private var presenter: LatestFragPresenter<*>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//     super.onViewCreated(view, savedInstanceState)
        //ButterKnife Binding
        ButterKnife.bind(this, view)



        //intiate arraylist
        imgList = ArrayList()

        presenter = LatestFragPresenter<LatestFragMvp.iLatestFragView>(LatestFragRepository())


        //RecyclerView Binding
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)




        showData()
    }

     private  fun showData() {

        GlobalScope.launch(Dispatchers.Main) {

            val res = RetroClass.getClient.getDetails().await()

            if (!imgList.isNullOrEmpty())
                imgList!!.clear()

            if (res.isSuccessful) {

                //Add Photo to list
                imgList!!.add(res.body()!!.photos!!)

                recyclerView.adapter =
                    PhotoAdapter(
                        activity!!,
                        res.body()!!.photos!!,
                        this@LatestFragment
                    )


            } else {

                Log.e(TAG, "Error Occured")

            }


        }
    }


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_latest, container, false)
    }*/


    /* private fun showData(): DisposableSingleObserver<Random> {

         swipeRefresh.isRefreshing = true

         return object : DisposableSingleObserver<Random>() {
             override fun onSuccess(t: Random) {

                 swipeRefresh.isRefreshing = false

                 if (!imgList.isNullOrEmpty())
                     imgList!!.clear()


                 if (t.photos?.size!! > 0) {

                     imgList!!.add(t.photos)

                     recyclerView.adapter =
                         PhotoAdapter(
                             activity!!,
                             t.photos,
                             this@LatestFragment
                         )
                 } else Log.e(TAG, "Some Error Occured")

             }

             override fun onError(e: Throwable) {

                 swipeRefresh.isRefreshing = false


             }
         }
     }*/


    fun calculateNoOfColumns(columnWidthDp: Float): Int { // For example columnWidthdp=180
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
                Toast.makeText(context, "Added To Favorites", Toast.LENGTH_SHORT).show()
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
