package app.matrix.wallpaperpexels.ui.activity.home

import app.matrix.wallpaperpexels.ui.activity.home.adapter.MainAdapter

class HomePresenter(private var view:iHomeView,private var tabadapter: MainAdapter):iHomePresenter {




    override fun setupViewPager() {
        view.setupViewPager(tabadapter)
    }


}