package app.matrix.wallpaperpexels.network.basenetwork

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build


object  NetworkUtil {

    private const val TYPE_WIFI = 1
    private const val TYPE_MOBILE = 2
    private const val TYPE_NOT_CONNECTED = 0


    private fun getConnectivityStatus(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: Network?
        val Network: NetworkInfo?
        val capabilities: NetworkCapabilities?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //Marshmallow or Up

            activeNetwork = cm.activeNetwork
            capabilities = cm.getNetworkCapabilities(activeNetwork)

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                return TYPE_WIFI

            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                return TYPE_MOBILE
            }

        } else {

            //Below Marshmallow

             Network = cm.activeNetworkInfo

            if (Network.type == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI
            } else if (Network.type == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE
            }

        }
        return TYPE_NOT_CONNECTED
    }


    fun getConnectivityStatusString(context: Context?): String? {
        val conn = NetworkUtil.getConnectivityStatus(context!!)
        var status: String? = null
        when (conn) {
            NetworkUtil.TYPE_WIFI -> status = "Wifi enabled"
            NetworkUtil.TYPE_MOBILE -> status = "Mobile data enabled"
            NetworkUtil.TYPE_NOT_CONNECTED -> status = "No Internet"
        }
        return status
    }


}
