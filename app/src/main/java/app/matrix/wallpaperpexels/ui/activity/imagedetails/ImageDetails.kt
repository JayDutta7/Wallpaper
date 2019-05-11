package app.matrix.wallpaperpexels.ui.activity.imagedetails

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.matrix.wallpaperpexels.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_details.*

class ImageDetails : AppCompatActivity(), ImageDetailsMVP.IimageDetailsView {


    companion object {
        private val TAG = ImageDetails::class.java.simpleName
    }



    private var imgDetailsPresenter: ImageDetailsPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        imgDetailsPresenter = ImageDetailsPresenter(this)

        imgDetailsPresenter?.getImages()

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

    override fun getImages() {
        if (!TextUtils.isEmpty(intent.getStringExtra("link"))) {
            GlideApp.with(this)
                .applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .load(intent.getStringExtra("link"))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(photo_view)
        }
    }


}

