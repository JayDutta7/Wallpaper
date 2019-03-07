package app.matrix.wallpaperpexels.utility


import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import app.matrix.wallpaperpexels.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class Utility {



    fun forgotPassword(context:Context) {

        val dialog = AlertDialog.Builder(context)
        val view = View.inflate(context, R.layout.forgot_password, null)
        val textInputLayoutEmail=view.findViewById(R.id.textInputLayoutEmail) as TextInputLayout
        val textInputEditTextEmail=view.findViewById(R.id.textInputEditTextEmail) as TextInputEditText
        val layout_PasswordSection=view.findViewById(R.id.layout_PasswordSection) as LinearLayoutCompat
        val textInputLayoutPassword=view.findViewById(R.id.textInputLayoutPassword) as TextInputLayout
        val textInputEditTextPassword=view.findViewById(R.id.textInputEditTextPassword) as TextInputEditText
        dialog.setView(view)
        dialog.setCancelable(false)




        dialog.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
        }
        dialog.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        // Finally, make the alert dialog using builder
        val alertdialog: AlertDialog = dialog.create()

        alertdialog.show()


    }

}