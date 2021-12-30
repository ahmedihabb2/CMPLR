package com.cmp.cmplr.View.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.FollowingAdapter
import com.cmp.cmplr.Adapter.NotesAdapter
import com.cmp.cmplr.Controller.FollowingController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.R
import com.cmp.cmplr.Shared.getImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FollowingFragment : Fragment() {
    lateinit var rv_showData: RecyclerView
    val followingRecyclerView: FollowingAdapter by lazy {
        FollowingAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.following_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val followingController = FollowingController()
        val localStorage = LocalStorage()
        val token : String = localStorage.getTokenData(requireActivity())!!
        super.onViewCreated(view, savedInstanceState)
        rv_showData = requireView().findViewById(R.id.following_recycler)
        rv_showData.adapter = followingRecyclerView
        lifecycleScope.launchWhenCreated {
            val followingList =followingController.fetchFollowingCont("Bearer $token")
            val listOfImgs : ArrayList<Pair<Bitmap,Bitmap>> = ArrayList()
            followingRecyclerView.setData(followingList)
            GlobalScope.launch {
                for (item in followingList)
                {
                    val header : Bitmap = getImage(item.header_image)!!
                    val avatar : Bitmap = getImage(item.avatar)!!
                    listOfImgs.add(Pair(header,avatar))
                }
                followingRecyclerView.setImages(listOfImgs)
            }.join()
            followingRecyclerView.notifyChanges()
        }
    }
}