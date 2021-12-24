package com.cmp.cmplr.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * This class acts as the data source manager for ViewPager2
 * ViewPager2 is the library used to implement sliding intro screens
 * @constructor
 * The constructor takes FragmentActivity as an argument in order to set the parent
 * class with it
 *
 * @param fa
 */
class SliderAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragmentList = ArrayList<Fragment>()

    /**
     * Get the number of Fragments (Screens to be used in swap)
     *
     * @return number of fragments
     */
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    /**
     * Creates a fragment depending on index you send
     *
     * @param position
     * @return Fragment
     */
    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }

    /**
     * Initializes the list with Fragments
     *
     * @param list
     */
    fun setFragmentList(list: List<Fragment>) {
        fragmentList.clear()
        fragmentList.addAll(list)
        notifyDataSetChanged()
    }
}