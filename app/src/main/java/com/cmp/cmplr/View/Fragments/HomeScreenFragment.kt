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

    lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.writePostBtn.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "teessst",Toast.LENGTH_SHORT).show()
            (activity as WritePostButtonEventHandler).onWritePostClicked(this)
        }
    }


    override fun onPostRequestDone(result: WritePostController.PostResult) {
        val id = (activity as WritePostButtonEventHandler).userID()

        setPosts(UserModelManager.getUserModel(id).getPosts())
    }

    private fun setPosts(newposts: ArrayList<String>) {
        Toast.makeText(activity?.applicationContext, "Post Added", Toast.LENGTH_LONG).show()
    }

    fun btnAction()
    {
        (activity as WritePostButtonEventHandler).onWritePostClicked(this)
    }
}