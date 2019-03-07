package app.matrix.wallpaperpexels.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.home.Home
import app.matrix.wallpaperpexels.login.LoginActivity


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        moveToNextPage()

    }

    private fun moveToNextPage() {

        Handler().postDelayed(
            {
              //  val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
                val mainIntent = Intent(this@SplashActivity, Home::class.java)
                startActivity(mainIntent)
                finish()
            },
            3000
        )
    }

}
