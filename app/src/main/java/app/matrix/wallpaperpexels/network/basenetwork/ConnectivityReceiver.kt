package app.matrix.wallpaperpexels.network.basenetwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ConnectivityReceiver: BroadcastReceiver() {

    private val TAG:String =ConnectivityReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {

        val status=NetworkUtil.getConnectivityStatusString(context)

        if(status.equals("No Internet")){
            Toast.makeText(context,status,Toast.LENGTH_SHORT).show()
            Log.e(TAG,status)
        }else{
            Toast.makeText(context,status,Toast.LENGTH_SHORT).show()
            Log.e(TAG,status)
        }

    }

    /*fun hasInternetConnection(): Observable<Boolean> {
        return Observable.fromCallable {
            try {
                // Connect to Google DNS to check for connection
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)

                socket.connect(socketAddress, timeoutMs)
                socket.close()

                true
            } catch (e: IOException) {
                false
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }*/


}