package app.matrix.wallpaperpexels.ui.fragment.home.latest


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.matrix.ticketingsystem.ui.base.BaseFragment
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.network.basenetwork.RetroClass
import app.matrix.wallpaperpexels.ui.activity.imagedetails.ImageDetails
import app.matrix.wallpaperpexels.ui.fragment.home.adapter.latestPhoto.PhotoAdapter
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Photos
import kotlinx.android.synthetic.main.fragment_latest.*
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



    private var imgList: MutableList<MutableList<Photos>>? = null

    private val TAG: String = LatestFragment::class.java.simpleName

    private var presenter: LatestFragPresenter<*>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//     super.onViewCreated(view, savedInstanceState)


        //intiate arraylist
        imgList = ArrayList()

        presenter = LatestFragPresenter<LatestFragMvp.iLatestFragView>(LatestFragRepository())

        //RecyclerView Binding
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)

        showData()

        presenter!!.fetchRandomApi()

    }
    override  fun fetchRandomApi() {

    }

     private fun showData() {

         GlobalScope.launch(Dispatchers.Main) {

             swipeRefresh.isRefreshing = true

             val res = RetroClass.getClient.getDetails().await()

             if (!imgList.isNullOrEmpty())
                 imgList!!.clear()

             if (res.isSuccessful) {
                 swipeRefresh.isRefreshing = false
                 //Add Photo to list
                 imgList!!.add(res.body()!!.photos!!)

                 recyclerView.adapter =
                     PhotoAdapter(
                         activity!!,
                         res.body()!!.photos!!,
                         this@LatestFragment
                     )


             } else {
                 swipeRefresh.isRefreshing = true

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
