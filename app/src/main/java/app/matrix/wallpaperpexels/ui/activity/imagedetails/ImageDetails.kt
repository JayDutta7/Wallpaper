package app.matrix.wallpaperpexels.ui.activity.imagedetails

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
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

class ImageDetails : AppCompatActivity(), IimageDetailsView {


    companion object {
        private val TAG = ImageDetails::class.java.simpleName
    }

    @BindView(R.id.photo_view)
    lateinit var photo_view: PhotoView

    private var imgDetailsPresenter: ImageDetailsPresenter? = null

    private var imgLink: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Binding ButterKnife
        ButterKnife.bind(this@ImageDetails)

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
        if (!TextUtils.isEmpty(intent.getStringExtra("link")))
            imgLink = intent.getStringExtra("link")
        GlideApp.with(this)
            .applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .load(imgLink)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photo_view)
    }





}

