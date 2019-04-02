package app.matrix.wallpaperpexels.ui.fragment.home.latest

import android.util.Log


class LatestFragRepository : LatestFragMvp.iLatestFragRepository {

    companion object {
        private val TAG = LatestFragRepository::class.java.simpleName
    }

    override  fun fetchRandomApi() {

        Log.e(TAG, "Invoked")

    }


}