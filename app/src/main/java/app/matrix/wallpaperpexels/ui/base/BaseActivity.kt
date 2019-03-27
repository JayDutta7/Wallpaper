package app.matrix.wallpaperpexels.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(), iBaseView {


    abstract fun getLayoutRes(): Int
    abstract fun getFragmentContainerId(): Int

    private var snackbar: Snackbar? = null
    private var context: Context? = this


    override fun onSuccess(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    override fun enableBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun disableBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun setTitle(msg: String) {
        supportActionBar?.title = msg
    }

    override fun setSubTitle(msg: String) {
        supportActionBar?.title = msg
    }

    override fun onAttachSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {

    }

    override fun setSwipeListener(listener: SwipeRefreshLayout.OnRefreshListener) {

    }

    override fun initializeSnackBar(view: View) {
        snackbar = Snackbar.make(view, "Please try again", Snackbar.LENGTH_LONG)

    }

    override fun showSnackBarMessage(message: String) {
        snackbar?.setText(message)
        snackbar?.setAction("Dismiss") {

        }
        snackbar?.show()
    }

    override fun onAttachFragment(fragment: Fragment?, tag: String) {
        when (fragment) {
            null -> return
            else -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(getFragmentContainerId(), fragment, tag)
                //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit()
            }
        }
    }

    override fun onAttachFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(getFragmentContainerId(), fragment, tag)

        if (addToBackStack)
            fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

    override fun onDetachFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tag)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragment?.let { fragmentTransaction.remove(it) }
    }

    override fun removeAllBackStackFragments() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun hideKeyBoard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }
}