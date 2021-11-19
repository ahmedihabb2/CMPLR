package com.cmp.cmplr.SharedModules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// Data source manager for ViewPager2
class SliderAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
    private val fragmentList = ArrayList<Fragment>()
    override fun getItemCount(): Int {
        return fragmentList.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
    fun setFragmentList(list: List<Fragment>) {
        fragmentList.clear()
        fragmentList.addAll(list)
        notifyDataSetChanged()
    }
}