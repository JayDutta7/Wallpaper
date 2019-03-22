package app.matrix.wallpaperpexels.ui.fragment.home.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photos(

    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("width")
    @Expose
    val width: Int?,
    @SerializedName("height")
    @Expose
    val height: Int?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("photographer")
    @Expose
    val photographer: String?,
    @SerializedName("photographer_url")
    @Expose
    val photographer_url: String?,
    @SerializedName("src")
    @Expose
    val src: Src
)

