package app.matrix.wallpaperpexels.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.home.pojo.Photos
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.photo_row.view.*

class PhotoAdapter(
    private val context: Context,
    private val dataList: MutableList<Photos>,
    private val clickPos: ClickedItem
) : RecyclerView.Adapter<PhotoAdapter.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(context).inflate(R.layout.photo_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.photoView.setOnClickListener {
            clickPos.clickpostion(position)
        }

        holder.imageSize.text =
            StringBuilder().append(dataList[position].height!!).append(" * ").append(dataList[position].width!!)

        Glide.with(context)

            .applyDefaultRequestOptions(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.placeloader)
                    .error(R.mipmap.ic_launcher)
                    .dontAnimate()
            ).load(dataList[position].src.original)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.photoView)

        holder.photographerName.text = dataList[position].photographer


    }


    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val photoView = view.photo_view!!
        val photographerName = view.photographer_name!!
        val imageSize = view.image_size!!


    }

}



