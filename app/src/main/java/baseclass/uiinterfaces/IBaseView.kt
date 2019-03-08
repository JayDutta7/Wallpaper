package baseclass.uiinterfaces

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by Matrix
 */

interface IBaseView {

    val baseContext: Context


    val bundleData: Bundle

    fun enableBackButton()
    fun disableBackButton()

    fun setTitle(title: String)
    fun setSubTitle(subTitle: String)
    fun setIcon(iconid: Drawable)

    fun onAttachSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout)
    fun setSwipeListener(listener: SwipeRefreshLayout.OnRefreshListener)

    fun onShowLoading()
    fun onHideLoading()

    fun showProgressDialog()
    fun hideProgressDialog()

    fun onFailed(message: String)
    fun onError(error: String)
    fun onSuccess(message: String)

    fun initializeSnackBar(view: View)
    fun showSnackBarMessage(message: String)

    fun onAttachFragment(fragment: Fragment, tag: String)
    fun onAttachFragment(fragment: Fragment, tag: String, addToBackStack: Boolean)
    fun onDetachFragment(tag: String)
    fun removeAllBackStackFragments()

    fun hideKeyboard(view: View)
}
