package app.matrix.wallpaperpexels.ui.activity.welcome


class WelComePresenter(private var _view: IWelcomeView, private var pageradapter: WelcomePagerAdapter) :
    IWelcomePresenter {


    override fun setUpPagerAdapter() {

        _view.setViewPagerAdapter(pageradapter)

    }


}