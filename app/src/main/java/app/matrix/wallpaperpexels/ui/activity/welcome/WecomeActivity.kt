package app.matrix.wallpaperpexels.ui.activity.welcome

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.database.StaticKT.Constant
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_wecome.*

class WecomeActivity : AppCompatActivity(), IWelcomeView {





    private var welComePresenter: WelComePresenter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wecome)

        welComePresenter = WelComePresenter(this, WelcomePagerAdapter(this))

        //Setup viewpager
        welComePresenter!!.setUpPagerAdapter()
        //Change
        welComePresenter!!.changeText()
        //Animation PagerAdapter
        welComePresenter!!.pagerAnimation()

    }

    override fun pagerAnimation() {
        class FadePageTransformer : ViewPager.PageTransformer{
            override fun transformPage(view: View, position: Float) {
                view.rotationY = position * -20
            }
        }
        //Set animation to adapter
        welcomePager.setPageTransformer(false,FadePageTransformer())
    }

    override fun changeText() {
        welcomePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if(position==2){
                    tvSkip.text = getString(R.string.welcomeNext)
                    tvSkip.setTextColor(Color.CYAN)
                }else{
                    tvSkip.text = getString(R.string.skip)
                    tvSkip.setTextColor(Color.WHITE)
                }
            }
        })
    }

    override fun setViewPagerAdapter(adapter: WelcomePagerAdapter) {
        welcomePager.adapter = adapter
        viewPagerCircleIndicator.setViewPager(welcomePager)

    }


    fun skipToLogin() {

        val mainIntent = Intent(this@WecomeActivity, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()

        WallPaperApp.getPref().save(Constant.WelcomeShown,true)


    }


}
