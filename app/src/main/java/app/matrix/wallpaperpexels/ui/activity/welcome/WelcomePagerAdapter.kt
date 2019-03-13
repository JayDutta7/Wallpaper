package app.matrix.wallpaperpexels.ui.activity.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class WelcomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    private var titleList: MutableList<String> = ArrayList()
    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {

        return fragmentList[position]
    }

    override fun getCount(): Int {

        return fragmentList.size
    }


    fun addPage(title: String, fragment: Fragment) {
        titleList.add(title)
        fragmentList.add(fragment)
    }

}