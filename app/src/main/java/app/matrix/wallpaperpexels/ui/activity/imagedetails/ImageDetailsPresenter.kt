package app.matrix.wallpaperpexels.ui.activity.imagedetails


class ImageDetailsPresenter(private var views: ImageDetailsMVP.IimageDetailsView): ImageDetailsMVP.IimageDetailsPresenter {
    override fun getImages() {
        views.getImages()
    }
}