package app.matrix.wallpaperpexels.ui.activity.home


import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.ui.activity.home.adapter.MainAdapter
import app.matrix.wallpaperpexels.ui.fragment.category.CategoryFragment
import app.matrix.wallpaperpexels.ui.fragment.latest.LatestFragment
import app.matrix.wallpaperpexels.ui.fragment.saved.SavedFragment
import butterknife.BindView
import butterknife.ButterKnife
import app.matrix.wallpaperpexels.ui.activity.home.navigationDrawer.BottomNavigationDrawerFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.content_home_main.*


class Home : AppCompatActivity(), iHomeView {



    private val TAG: String = Home::class.java.simpleName

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout

    private var homePresenter: HomePresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)
        setSupportActionBar(bottom_app_bar)
        //ButterKnife Binding
        ButterKnife.bind(this@Home)

        homePresenter= HomePresenter(this,MainAdapter(supportFragmentManager))
        //Main Adapter
        homePresenter!!.setupViewPager()

       // initialize()


    }//end of oncreate



    override fun setupViewPager(adapter: MainAdapter) {
        adapter.addFragment(LatestFragment(), "Latest")
        adapter.addFragment(CategoryFragment(), "Category")
        adapter.addFragment(SavedFragment(), "Favorite")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottomappbar_menu, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.app_bar_settings -> toast(getString(R.string.settings_clicked))
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    // This is an extension method for easy Toast call
    private fun toast(message: CharSequence) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }


}
