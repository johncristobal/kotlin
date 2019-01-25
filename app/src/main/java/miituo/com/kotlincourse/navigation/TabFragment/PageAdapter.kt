package miituo.com.kotlincourse.navigation.TabFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import miituo.com.kotlincourse.navigation.TabFragment.fragmentsTabs.OneFragment
import miituo.com.kotlincourse.navigation.TabFragment.fragmentsTabs.TwoFragment

//conjstrucot default
class PageAdapter(fm: FragmentManager?, val numTabs:Int) : FragmentStatePagerAdapter(fm) {



    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> return OneFragment()
            1 -> return TwoFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return numTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> return "Uno"
            1 -> return "Dos"
            else -> return null
        }
    }
}