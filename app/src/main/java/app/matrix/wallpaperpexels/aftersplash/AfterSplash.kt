package app.matrix.wallpaperpexels.aftersplash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.aftersplash.contract.AfterSplashInterface
import app.matrix.wallpaperpexels.aftersplash.presenter.AfterSplashPresenter
import app.matrix.wallpaperpexels.login.LoginActivity
import app.matrix.wallpaperpexels.registration.RegistrationActivity
import butterknife.BindView
import butterknife.ButterKnife

class AfterSplash : AppCompatActivity(), AfterSplashInterface.View {

    private var presenter: AfterSplashPresenter? = null

    override fun loginClicked() {
        val mainIntent = Intent(this@AfterSplash, LoginActivity::class.java)
        startActivity(mainIntent)

    }

    override fun registrationClicked() {
        val mainIntent = Intent(this@AfterSplash, RegistrationActivity::class.java)
        startActivity(mainIntent)

    }

    @BindView(R.id.clicktologin)
    lateinit var clicktologin: AppCompatButton

    @BindView(R.id.clicktoregistration)
    lateinit var clicktoregistration: AppCompatButton


    override fun init() {
        ButterKnife.bind(this)




        clicktologin.setOnClickListener {
            presenter?.clickLogin()
        }

        clicktoregistration.setOnClickListener {
            presenter?.clickRegistration()

        }


    }


    companion object {
        private val TAG: String = AfterSplash::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aftersplash)


        presenter = AfterSplashPresenter(this)

    }


}
