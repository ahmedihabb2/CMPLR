package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.Model.managers.UserModelManager
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.WritePostButtonEventHandler
import com.cmp.cmplr.View.Activities.WritePostRequester
import com.cmp.cmplr.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment(),
                           WritePostRequester {

    private lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.writePostBtn.setOnClickListener {
            (activity as WritePostButtonEventHandler)
                .onWritePostClicked(this)
        }


        return view
    }



    override fun onPostRequestDone(result: WritePostController.PostResult) {
        val id = "nouser"

        setPosts(UserModelManager.getUserModel(id).getPosts())
    }

    private fun setPosts(newposts: ArrayList<String>) {
        binding.testView.loadDataWithBaseURL(null, newposts[0], "text/html", "utf-8", null)
    }

}