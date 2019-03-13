package app.matrix.wallpaperpexels.ui.fragment


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import app.matrix.wallpaperpexels.GlideApp

import app.matrix.wallpaperpexels.R
import app.matrix.wallpaperpexels.localdatabase.Constant
import butterknife.BindView
import butterknife.ButterKnife

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */


class SlideFragment : Fragment() {


    @BindView(R.id.imgSlides)
    lateinit var imgSlides: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_slide, container, false)
        ButterKnife.bind(activity)

        val bundle = arguments
        if (bundle != null) {
            val id=bundle.getInt(Constant.IMAGE)

            GlideApp.with(activity)
                .load(id)
                .into(imgSlides)

        }



        return view
    }


}
