package app.matrix.wallpaperpexels.ui.activity.imagedetails

import app.matrix.wallpaperpexels.ui.base.iBasePresenter
import app.matrix.wallpaperpexels.ui.base.iBaseView

interface ImageDetailsMVP {

    interface imageDetailsView:iBaseView{

        fun getImages()
    }

    interface imageDetailsPresenter<V:iBaseView>:iBasePresenter<V>{

        fun getImages()

    }

    interface imageDetailsRepository{


    }



}