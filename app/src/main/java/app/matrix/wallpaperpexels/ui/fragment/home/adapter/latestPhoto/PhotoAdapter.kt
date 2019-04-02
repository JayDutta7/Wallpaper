package app.matrix.wallpaperpexels.ui.fragment.home.adapter.latestPhoto

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import app.matrix.wallpaperpexels.GlideApp
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.latestPhotoRes.Photos
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.photo_row.view.*
import java.util.*

class PhotoAdapter(
    private val context: FragmentActivity,
    private val dataList: MutableList<Photos>,
    private val clickPos: ClickedItem
) : RecyclerView.Adapter<PhotoAdapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(context).inflate(
                R.layout.photo_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.photoView.setOnClickListener {
            clickPos.clickpostion(position,"Details")
        }

        holder.imageSize.text =
            StringBuilder().append(dataList[position].height!!).append(" * ").append(dataList[position].width!!)


        GlideApp.with(context)
            .applyDefaultRequestOptions(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(getRandomDrawbleColor())
                    .error(getRandomDrawbleColor())
                    .dontAnimate()
            )
            .load(dataList[position].src?.large2x)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.photoView)

        holder.favorite.setOnClickListener {
            clickPos.clickpostion(position,"Favorite")
            holder.favorite.setImageResource(R.drawable.ic_like)
        }


        holder.photographerName.text = dataList[position].photographer


    }


    private fun getRandomDrawbleColor(): ColorDrawable {
        return ColorDrawable(Color.rgb(Random().nextInt(255), Random().nextInt(255), Random().nextInt(255)))
    }


    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val photoView = view.photo_view!!
        val photographerName = view.photographer_name!!
        val imageSize = view.image_size!!
        val favorite = view.click_favorite!!
    }

}



