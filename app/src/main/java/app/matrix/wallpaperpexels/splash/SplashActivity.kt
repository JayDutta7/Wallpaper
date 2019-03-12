package app.matrix.wallpaperpexels.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.aftersplash.AfterSplash
import app.matrix.wallpaperpexels.home.Home
import app.matrix.wallpaperpexels.localdatabase.Constant
import app.matrix.wallpaperpexels.localdatabase.LocalSharedPreference
import app.matrix.wallpaperpexels.login.LoginActivity
import app.matrix.wallpaperpexels.splash.contract.ContractSplashInterface
import app.matrix.wallpaperpexels.splash.presenter.SplashPresenter


class SplashActivity : AppCompatActivity(), ContractSplashInterface.View {


    override fun usePreference() {
        localdatabase = LocalSharedPreference(this)

    }

    private var presenter: SplashPresenter? = null

    private var localdatabase: LocalSharedPreference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this)

    }

    override fun moveToNextPage() {

        if (!TextUtils.isEmpty(localdatabase?.getValueString(Constant.UserEmail))) {

            val mainIntent = Intent(this@SplashActivity, Home::class.java)
            // val mainIntent = Intent(this@SplashActivity, Home::class.java)
            startActivity(mainIntent)
            finish()

        } else {

            Handler().postDelayed(
                {
                    val mainIntent = Intent(this@SplashActivity, AfterSplash::class.java)
                    // val mainIntent = Intent(this@SplashActivity, Home::class.java)
                    startActivity(mainIntent)
                    finish()
                },
                3000
            )
        }
    }

}
