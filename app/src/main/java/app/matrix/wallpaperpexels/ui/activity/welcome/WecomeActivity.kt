package app.matrix.wallpaperpexels.ui.activity.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.ViewPager
import app.matrix.wallpaperpexels.R
import butterknife.BindView
import butterknife.ButterKnife
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

        welComePresenter= WelComePresenter(this,WelcomePagerAdapter(supportFragmentManager))
        welComePresenter!!.setUpPagerAdapter()

    }

    override fun setViewPagerAdapter(adapter: WelcomePagerAdapter) {

        welcomePager.adapter=adapter
        viewPagerCircleIndicator.setViewPager(welcomePager)
    }


}
