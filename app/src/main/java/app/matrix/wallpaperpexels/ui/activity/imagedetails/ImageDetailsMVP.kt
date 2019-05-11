package app.matrix.wallpaperpexels.ui.activity.imagedetails

import app.matrix.ticketingsystem.ui.base.iBasePresenter
import app.matrix.ticketingsystem.ui.base.iBaseView

interface ImageDetailsMVP {

    interface IimageDetailsView: iBaseView {

        fun getImages()
    }

    interface IimageDetailsPresenter<V:iBaseView>: iBasePresenter<V> {

        fun getImages()

    }

    interface imageDetailsRepository{


    }



}