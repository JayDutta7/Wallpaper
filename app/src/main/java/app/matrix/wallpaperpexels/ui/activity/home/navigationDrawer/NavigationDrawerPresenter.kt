package app.matrix.wallpaperpexels.ui.activity.home.navigationDrawer

class NavigationDrawerPresenter(private var _view:iNavigationDrawerView):iNavigationDrawerPresenter {
    override fun settings() {
        _view.settings()
    }

    override fun account() {
       _view.account()
    }

    override fun share() {
       _view.share()
    }

    override fun about() {
        _view.about()
    }

    override fun SignOut() {
        _view.SignOut()
    }


}