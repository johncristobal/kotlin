package miituo.com.kotlincourse.navigation.TabFragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_third.*

import miituo.com.kotlincourse.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ThirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tablayoutt : TabLayout = tabis
        tablayoutt.addTab(tablayoutt.newTab().setText("Uno"))
        tablayoutt.addTab(tablayoutt.newTab().setText("Dos"))

        val vpager : ViewPager = pager
        val adapter = PageAdapter(fragmentManager, tablayoutt.tabCount)
        vpager.adapter = adapter

        tablayoutt.setupWithViewPager(vpager)
    }



}
