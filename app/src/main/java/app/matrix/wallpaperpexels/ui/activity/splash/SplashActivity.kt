package app.matrix.wallpaperpexels.ui.activity.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.ui.activity.home.Home
import app.matrix.wallpaperpexels.ui.activity.login.LoginActivity
import app.matrix.wallpaperpexels.ui.activity.welcome.WecomeActivity
import butterknife.BindView
import butterknife.ButterKnife


class SplashActivity : AppCompatActivity(), ISplashView {


    @BindView(R.id.includeSplash2)
    lateinit var includeSplash2: View

    @BindView(R.id.splashLogo)
    lateinit var splashLogo: AppCompatImageView


    companion object {
        private val TAG = SplashActivity::javaClass.name
        private const val FADE_OUT_TIME: Long = 1000
        private const val FADE_IN_TIME: Long = 1000
    }

    private var presenter: ISplashPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)

        presenter = SplashPresenter(this)


        presenter?.onAnimate()


    }

    override fun oneTimeWelcome() {
        val mainIntent = Intent(this@SplashActivity, WecomeActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun moveToLoginPage() {
        val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun moveToHomePage() {
        val mainIntent = Intent(this@SplashActivity, Home::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun moveToAnimatedPage() {
        animate()
    }


    private fun animate() {
        val fadeOut = ObjectAnimator.ofFloat(splashLogo, "alpha", 1f, .0f)
        fadeOut.duration = FADE_OUT_TIME
        fadeOut.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                includeSplash2.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        val fadeIn = ObjectAnimator.ofFloat(includeSplash2, "alpha", .2f, 1f)
        fadeIn.duration = FADE_IN_TIME
        fadeIn.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {


                presenter?.goToNextPage()


            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })

        val mAnimationSet = AnimatorSet()
        mAnimationSet.play(fadeIn).after(fadeOut)
        mAnimationSet.start()
    }

    //OnDestroy for prevent memory leak if activity is no longer visible
    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

}
