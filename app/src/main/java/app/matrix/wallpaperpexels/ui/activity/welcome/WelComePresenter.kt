package app.matrix.wallpaperpexels.ui.activity.welcome


class WelComePresenter  (private var _view: IWelcomeView, private var pageradapter: WelcomePagerAdapter) :
    IWelcomePresenter {

    override fun pagerAnimation() {
        _view.pagerAnimation()
    }

    override fun changeText() {
        _view.changeText()
    }

    override fun setUpPagerAdapter() {
        _view.setViewPagerAdapter(pageradapter)
    }


}