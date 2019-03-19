package app.matrix.wallpaperpexels.ui.activity.imagedetails


class ImageDetailsPresenter(private var views:IimageDetailsView):IimageDetailsPresenter {
    override fun getImages() {
        views.getImages()
    }
}