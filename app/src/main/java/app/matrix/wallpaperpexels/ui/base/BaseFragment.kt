package app.matrix.wallpaperpexels.ui.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class BaseFragment:Fragment(),iBaseView {



    override fun enableBack() {

    }

    override fun disableBack() {

    }

    override fun setTitle(msg: String) {

    }

    override fun setSubTitle(msg: String) {

    }

    override fun onAttachSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {

    }

    override fun setSwipeListener(listener: SwipeRefreshLayout.OnRefreshListener) {

    }

    override fun initializeSnackBar(view: View) {

    }

    override fun showSnackBarMessage(message: String) {

    }

    override fun onAttachFragment(fragment: Fragment?, tag: String) {

    }

    override fun onAttachFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {


    }

    override fun onDetachFragment(tag: String) {


    }

    override fun removeAllBackStackFragments() {


    }

    override fun hideKeyBoard(view: View) {


    }

    override fun onSuccess(msg: String) {


    }

    override fun onError(msg: String) {


    }

    override fun onFailed(msg: String) {


    }
}