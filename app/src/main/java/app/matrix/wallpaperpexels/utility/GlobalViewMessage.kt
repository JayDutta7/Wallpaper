package app.matrix.wallpaperpexels.utility

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import app.matrix.wallpaperpexels.R
import com.google.android.material.snackbar.Snackbar


class GlobalViewMessage{


    companion object {

        /*------------------This is for Global Toast -------------------*/
        fun showShortToast(msg: String?, ctx: Context?) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }

        fun showLongToast(msg: String?, ctx: Context?) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show()
        }
        /*------------------This is for Global Snackbar -------------------*/
        fun showSnackBarShort(view:View?,msg: CharSequence?,context:Context?){
            Snackbar.make(view!!, msg!!, Snackbar.LENGTH_SHORT)
                .setActionTextColor(ContextCompat.getColor(context!!, R.color.primary_material_light))
                .show()
        }
        fun showSnackBarLong(view:View?,msg: CharSequence?,context:Context?){
            Snackbar.make(view!!, msg!!, Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(context!!,R.color.primary_material_light))
                .show()
        }


    }



}