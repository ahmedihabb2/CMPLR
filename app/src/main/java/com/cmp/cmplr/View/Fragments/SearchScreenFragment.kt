package com.cmp.cmplr.View.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.cmp.cmplr.Shared.ExploreAdapter
import com.cmp.cmplr.Shared.NotesAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout


class SearchScreenFragment : Fragment() {
    lateinit var rv_showData : RecyclerView
    lateinit var rv_showData2 : RecyclerView
    lateinit var rv_showData3 : RecyclerView
    lateinit var rv_showData4 : RecyclerView
    lateinit var rv_showData5 : RecyclerView
    val exploreItemsRecyclerView : ExploreAdapter by lazy {
        ExploreAdapter()
    }
    val exploreItemsRecyclerView1 : ExploreAdapter by lazy {
        ExploreAdapter()
    }
    val exploreItemsRecyclerView2 : ExploreAdapter by lazy {
        ExploreAdapter()
    }
    val exploreItemsRecyclerView3 : ExploreAdapter by lazy {
        ExploreAdapter()
    }
    val exploreItemsRecyclerView4 : ExploreAdapter by lazy {
        ExploreAdapter()
    }
    val exploreItemsRecyclerView5: ExploreAdapter by lazy {
        ExploreAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_screen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coll_toolbar : CollapsingToolbarLayout = view.findViewById(R.id.collapse_toolbar)
        val search_btn : Button = view.findViewById(R.id.search_btn)
        search_btn.setOnClickListener {
            view.findNavController().navigate(R.id.action_searchScreenFragment_to_searchInput)
        }
        coll_toolbar.setContentScrimColor(Color.BLACK)
        rv_showData = view.findViewById(R.id.items)
        rv_showData.adapter = exploreItemsRecyclerView
        rv_showData2= view.findViewById(R.id.items2)
        rv_showData2.adapter = exploreItemsRecyclerView2
        rv_showData3 = view.findViewById(R.id.items3)
        rv_showData3.adapter = exploreItemsRecyclerView3
        rv_showData4 = view.findViewById(R.id.items4)
        rv_showData4.adapter = exploreItemsRecyclerView4
        rv_showData5 = view.findViewById(R.id.items5)
        rv_showData5.adapter = exploreItemsRecyclerView5
        var images_list: ArrayList<Int> = ArrayList()
        for (i in 1..9)
        {
            images_list.add(R.drawable.ourlogo)
        }
        exploreItemsRecyclerView.setList(images_list)
        exploreItemsRecyclerView2.setList(images_list)
        exploreItemsRecyclerView3.setList(images_list)
        exploreItemsRecyclerView4.setList(images_list)
        exploreItemsRecyclerView5.setList(images_list)
    }

}