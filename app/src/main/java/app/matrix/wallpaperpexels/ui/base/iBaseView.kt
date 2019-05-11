package app.matrix.ticketingsystem.ui.base

import android.content.Context
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

interface iBaseView {

    //Getting Base Context
    fun getBaseContext(): Context

    //Backbutton
    fun enableBack()
    fun disableBack()

    //SetTitle
    fun setTitle(msg:String)
    fun setSubTitle(msg:String)

    //SwipeRefresh Layout
    fun onAttachSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout)
    fun setSwipeListener(listener: SwipeRefreshLayout.OnRefreshListener)

    //SnackBar
    fun initializeSnackBar(view: View)
    fun showSnackBarMessage(message: String)

    //Fragment
    fun onAttachFragment(@NonNull fragment: Fragment?, @NonNull tag: String)
    fun onAttachFragment(@NonNull fragment: Fragment, @NonNull tag: String, addToBackStack: Boolean)
    fun onDetachFragment(@NonNull tag: String)
    fun removeAllBackStackFragments()

    //keyboard hide
    fun hideKeyBoard(view:View)

    //Message
    fun onSuccess(msg:String)
    fun onError(msg:String)
    fun onFailed(msg:String)


}