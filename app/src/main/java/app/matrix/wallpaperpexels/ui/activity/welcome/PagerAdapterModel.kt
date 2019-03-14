package app.matrix.wallpaperpexels.ui.activity.welcome

import app.matrix.wallpaperpexels.R

enum class PagerAdapterModel(val titleResId: Int, val layoutResId: Int) {
    RED(R.string.one, R.layout.layout_one),
    BLUE(R.string.two, R.layout.layout_two),
    GREEN(R.string.three, R.layout.layout_three)
}