package app.matrix.wallpaperpexels.ui.activity.welcome

import android.os.Bundle
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.ui.fragment.SlideFragment

class WelComePresenter(private var _view: IWelcomeView, private var pageradapter: WelcomePagerAdapter) : IWelcomePresenter {


    private var drawables =
        intArrayOf(R.drawable.welcome_1, R.drawable.welcome_2, R.drawable.welcome_3)

    override fun setUpPagerAdapter() {
        for (i in drawables.indices) {

            val fragment = SlideFragment()
            val bundle = Bundle()
            bundle.putInt(Constant.IMAGE, drawables[i])
            fragment.arguments = bundle

           // pageradapter.addPage("", fragment)
        }

        _view.setViewPagerAdapter(pageradapter)
    }


}