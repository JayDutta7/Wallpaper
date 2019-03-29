package app.matrix.wallpaperpexels.ui.activity.welcome

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.ViewPager
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import me.relex.circleindicator.CircleIndicator

class WecomeActivity : AppCompatActivity(), IWelcomeView {




    @BindView(R.id.welcomePager)
    lateinit var welcomePager: ViewPager
    @BindView(R.id.viewPagerCircleIndicator)
    lateinit var viewPagerCircleIndicator: CircleIndicator
    @BindView(R.id.tvSkip)
    lateinit var tvSkip: AppCompatTextView

    private var welComePresenter: WelComePresenter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wecome)
        ButterKnife.bind(this)

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

    @OnClick(R.id.tvSkip)
    fun skipToLogin() {

        val mainIntent = Intent(this@WecomeActivity, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()

        WallPaperApp.getPref().save(Constant.WelcomeShown,true)


    }


}
