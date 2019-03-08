package app.matrix.wallpaperpexels.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.home.adapter.PhotoAdapter
import app.matrix.wallpaperpexels.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.home.pojo.Photos
import app.matrix.wallpaperpexels.home.pojo.Random
import app.matrix.wallpaperpexels.imagedetails.ImageDetails
import app.matrix.wallpaperpexels.login.LoginActivity
import app.matrix.wallpaperpexels.network.ApiInterface
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_main.*
import kotlinx.android.synthetic.main.app_bar_home_main.*
import retrofit2.Call
import retrofit2.Response



class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ClickedItem {


    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.swipeRefresh)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private var imgList: MutableList<MutableList<Photos>>? = null

    private var mAPIService: ApiInterface? = null


    private val TAG: String = Home::class.java.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)
        setSupportActionBar(toolbar)

        initialize()
    }//end of oncreate

    fun initialize() {
        //ButterKnife Binding
        ButterKnife.bind(this@Home)
        //initialize retrofit
        mAPIService = ApiInterface.CreateRetrofit.apiService
        //initiate Unspalsh


        //intiate arraylist
        imgList = ArrayList()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //RecyclerView Binding
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        showData()

        /*signOut.setOnClickListener {
            signOut()
        }*/
    }



    private fun signOut() {
        val mainIntent = Intent(this@Home, LoginActivity::class.java)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(mainIntent)

        if (FirebaseAuth.getInstance() != null)
        //Logout from Firebase
            FirebaseAuth.getInstance().signOut()
    }

    private fun showData() {


        mAPIService!!.getDetails().enqueue(object : retrofit2.Callback<Random> {

            override fun onFailure(call: Call<Random>, t: Throwable) {

                Log.e(TAG, "Failure" + t.message)

            }

            override fun onResponse(call: Call<Random>, response: Response<Random>) {

                Log.e(TAG, "Sucess" + response.body()?.photos?.size!!)

                when {
                    !imgList.isNullOrEmpty() -> imgList!!.clear()
                    response.body()?.photos?.size!! > 0 -> imgList!!.add(response.body()!!.photos!!)
                    else -> Log.e(TAG, "API NO_RESAPONSE")
                }

                when {
                    response.body()?.photos?.size!! > 0 -> recyclerView.adapter =
                        PhotoAdapter(this@Home, response.body()!!.photos!!, this@Home)
                    else -> Log.e(TAG, "Some Error Occured")
                }
            }

        })
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.nav_signout -> {
                signOut()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //Interface
    override fun clickpostion(Position: Int) {
        for (i in 0 until imgList!!.size) {


            //Move
            movetoDetail(imgList?.get(i)?.get(Position)?.src?.original)
        }
    }

    private fun movetoDetail(imgpath: String?) {

        val mainIntent = Intent(this@Home, ImageDetails::class.java)
        mainIntent.putExtra("link", imgpath)
        startActivity(mainIntent)


    }


}
