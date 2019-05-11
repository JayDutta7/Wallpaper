package app.matrix.wallpaperpexels.ui.fragment.home.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.ui.fragment.home.adapter.categoryWisePhoto.CategoryAdapter
import app.matrix.wallpaperpexels.ui.fragment.home.interfaces.ClickedItem
import app.matrix.wallpaperpexels.ui.fragment.home.pojo.categoryData.CategoryData

import kotlinx.android.synthetic.main.fragment_category.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoryFragment : Fragment(), iCategoryFragView, ClickedItem {

    override fun clickpostion(Position: Int,Msg:String) {
        Toast.makeText(activity!!, Msg, Toast.LENGTH_SHORT).show()
    }



    private val list = ArrayList<CategoryData>()

    private val myCategoryList = intArrayOf(
        R.drawable.ic_abstract,
        R.drawable.ic_animals,
        R.drawable.ic_art,
        R.drawable.ic_car,
        R.drawable.ic_city,
        R.drawable.ic_dance,
        R.drawable.ic_flowers,
        R.drawable.ic_food,
        R.drawable.ic_music,
        R.drawable.ic_nature,
        R.drawable.ic_space,
        R.drawable.ic_sports
    )

    private val categoryNameList = arrayOf(
        "Abstract",
        "Animals",
        "Art",
        "Car",
        "City",
        "Dance",
        "Flower",
        "Food",
        "Music",
        "Nature",
        "Space",
        "Sports"
        /*getString(R.string.cat_1),
        getString(R.string.cat_2),
        getString(R.string.cat_3),
        getString(R.string.cat_4),
        getString(R.string.cat_5),
        getString(R.string.cat_6),
        getString(R.string.cat_7),
        getString(R.string.cat_8),
        getString(R.string.cat_9),
        getString(R.string.cat_10),
        getString(R.string.cat_11),
        getString(R.string.cat_12)*/
    )


    private fun populateCategoryList(): ArrayList<CategoryData> {

        for (i in 0..11) {
            list.add(CategoryData(categoryNameList[i], myCategoryList[i]))
        }

        return list
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //RecyclerView Binding
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CategoryAdapter(activity!!, populateCategoryList(), this@CategoryFragment)

    }
}
