package app.matrix.wallpaperpexels.home.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Src(
    @SerializedName("original")
    @Expose
    val original: String?,
    @SerializedName("large2x")
    @Expose
    val large2x: String?,
    @SerializedName("large")
    @Expose
    val large: String?,
    @SerializedName("medium")
    @Expose
    val medium: String?,
    @SerializedName("portrait")
    @Expose
    val portrait: String?,
    @SerializedName("landscape")
    @Expose
    val landscape: String?,
    @SerializedName("small")
    @Expose
    val small: String?,
    @SerializedName("square")
    @Expose
    val square: String?,
    @SerializedName("tiny")
    @Expose
    val tiny: String?


)

