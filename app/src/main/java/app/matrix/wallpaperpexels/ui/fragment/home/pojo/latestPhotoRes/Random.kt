package app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Random(
    @SerializedName("page")
    @Expose
    val page: Int?,
    @SerializedName("per_page")
    @Expose
    val per_page: Int?,
    @SerializedName("next_page")
    @Expose
    val next_page: String,
    @SerializedName("photos")
    @Expose
    val photos: MutableList<Photos>?
)
