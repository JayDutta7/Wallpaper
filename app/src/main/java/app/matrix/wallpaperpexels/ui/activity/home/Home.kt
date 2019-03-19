package app.matrix.wallpaperpexels.ui.activity.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.ui.activity.home.adapter.MainAdapter
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import app.matrix.wallpaperpexels.ui.fragment.CategoryFragment
import app.matrix.wallpaperpexels.ui.fragment.LatestFragment
import app.matrix.wallpaperpexels.ui.fragment.SavedFragment
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_main.*
import kotlinx.android.synthetic.main.app_bar_home_main.*


class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val TAG: String = Home::class.java.simpleName

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)
        setSupportActionBar(toolbar)

        initialize()
    }//end of oncreate

    private fun initialize() {
        //ButterKnife Binding
        ButterKnife.bind(this@Home)


       /* fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val adapter = MainAdapter(supportFragmentManager)

        adapter.addFragment(LatestFragment(), "Latest")
        adapter.addFragment(CategoryFragment(), "Category")
        adapter.addFragment(SavedFragment(), "Favorite")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


    }


    private fun signOut() {


        if (FirebaseAuth.getInstance() != null)
        //Logout from Firebase
            FirebaseAuth.getInstance().signOut()

        //Clear Local Database
        WallPaperApp.getPref().clearSharedPreference()

        val mainIntent = Intent(this@Home, LoginActivity::class.java)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(mainIntent)


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


}
