package app.matrix.wallpaperpexels.ui.activity.home.navigationDrawer

interface iNavigationDrawerPresenter {
    fun initView()

    fun settings()
    fun account()
    fun share()
    fun about()
    fun SignOut()
}