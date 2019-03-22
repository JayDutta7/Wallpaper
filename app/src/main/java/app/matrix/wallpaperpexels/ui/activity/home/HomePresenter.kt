package app.matrix.wallpaperpexels.ui.activity.home

import app.matrix.wallpaperpexels.ui.activity.home.adapter.SlideAdapter

class HomePresenter(private var view:iHomeView,private var tabadapter: SlideAdapter):iHomePresenter {




    override fun setupViewPager() {
        view.setupViewPager(tabadapter)
    }


}