package com.example.bottomappbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.WallPaperApp
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_bottomsheet.*

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {
                R.id.nav_camera -> toast(getString(R.string.nav1_clicked))
                R.id.nav_gallery -> toast(getString(R.string.nav1_clicked))
                R.id.nav_manage -> toast(getString(R.string.nav1_clicked))
                R.id.nav_share -> toast(getString(R.string.nav1_clicked))
                R.id.nav_send -> toast(getString(R.string.nav1_clicked))
                R.id.nav_signout -> signOut()
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }

    // This is an extension method for easy Toast call
    private fun toast(message: CharSequence) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 600)
        toast.show()
    }

    private fun signOut() {


        if (FirebaseAuth.getInstance() != null)
        //Logout from Firebase
            FirebaseAuth.getInstance().signOut()

        //Clear Local Database
        WallPaperApp.getPref().clearSharedPreference()

        val mainIntent = Intent(activity, LoginActivity::class.java)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity!!.startActivity(mainIntent)

    }

}