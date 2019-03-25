package app.matrix.wallpaperpexels.ui.fragment.home.adapter.categoryWisePhoto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.categoryData.CategoryData
import kotlinx.android.synthetic.main.category_row.view.*

class CategoryAdapter(
    private val context: FragmentActivity,
    private val dataList: MutableList<CategoryData>,
    private val clickPos: ClickedItem
) : RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(context).inflate(
                R.layout.category_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.categoryName.text = dataList[position].catname

        holder.categoryphotoView.setImageResource(dataList[position].catImage!!)

        holder.categoryCardClick.setOnClickListener {
            clickPos.clickpostion(position)
        }

    }


    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val categoryphotoView = view.drawable_img!!
        val categoryName = view.category_name!!
        val categoryCardClick = view.category_card!!
    }
}