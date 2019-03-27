package app.matrix.wallpaperpexels.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class BaseFragment : Fragment(), iBaseView {

    private var baseActivity: BaseActivity? = null
    abstract fun getLayoutRes(): Int


    override fun getBaseContext(): Context {
        return context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseActivity = context as BaseActivity

    }

    override fun enableBack() {
        baseActivity!!.enableBack()
    }

    override fun disableBack() {
        baseActivity!!.disableBack()
    }

    override fun setTitle(msg: String) {
        baseActivity!!.setTitle(msg)
    }

    override fun setSubTitle(msg: String) {
        baseActivity!!.setSubTitle(msg)
    }

    override fun onAttachSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {

    }

    override fun setSwipeListener(listener: SwipeRefreshLayout.OnRefreshListener) {

    }

    override fun initializeSnackBar(view: View) {
        baseActivity!!.initializeSnackBar(view)
    }

    override fun showSnackBarMessage(message: String) {
        baseActivity!!.showSnackBarMessage(message)
    }

    override fun onAttachFragment(fragment: Fragment?, tag: String) {
        baseActivity!!.onAttachFragment(fragment, tag)
    }

    override fun onAttachFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {

        baseActivity!!.onAttachFragment(fragment, tag, addToBackStack)
    }

    override fun onDetachFragment(tag: String) {

        baseActivity!!.onDetachFragment(tag)

    }

    override fun removeAllBackStackFragments() {
        baseActivity!!.removeAllBackStackFragments()
    }

    override fun hideKeyBoard(view: View) {
        baseActivity!!.hideKeyBoard(view)
    }

    override fun onSuccess(msg: String) {

        baseActivity!!.onSuccess(msg)

    }

    override fun onError(msg: String) {

        baseActivity!!.onError(msg)
    }

    override fun onFailed(msg: String) {

        baseActivity!!.onFailed(msg)
    }

}