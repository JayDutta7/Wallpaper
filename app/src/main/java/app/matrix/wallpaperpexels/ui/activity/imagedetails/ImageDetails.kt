package app.matrix.wallpaperpexels.ui.activity.imagedetails

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.matrix.wallpaperpexels.GlideApp
import app.matrix.wallpaperpexels.R
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_details.*

class ImageDetails : AppCompatActivity() {

    companion object {
        private val TAG = ImageDetails::class.java.simpleName
    }

    @BindView(R.id.photo_view)
    lateinit var photo_view: PhotoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initView()
    }

    private fun initView() {
        //Binding ButterKnife
        ButterKnife.bind(this@ImageDetails)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val ImgLink: String? = intent.getStringExtra("link")

        Log.e(TAG, ImgLink)

        GlideApp.with(this)
            .applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .load(ImgLink)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photo_view)


    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_download -> {
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_setwallpaper -> {
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}

