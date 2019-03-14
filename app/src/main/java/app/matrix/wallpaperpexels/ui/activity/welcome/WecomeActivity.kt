package app.matrix.wallpaperpexels.ui.activity.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        welComePresenter!!.setUpPagerAdapter()


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
