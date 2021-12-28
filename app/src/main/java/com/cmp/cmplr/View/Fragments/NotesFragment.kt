package com.cmp.cmplr.View.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.cmp.cmplr.Adapter.NotesAdapter
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.NotesController
import com.cmp.cmplr.Shared.getImage
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NotesFragment : Fragment() {
    lateinit var rv_showData : RecyclerView
    val notesRecyclerView : NotesAdapter by lazy {
        NotesAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.notes_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localStorage = LocalStorage()
        val post_id : Int= arguments?.get("post_id") as Int
        val notesController = NotesController()
        val is_loading : ProgressBar = view.findViewById(R.id.comments_load)
        val likes_count : TextView = view.findViewById(R.id.likes_count)
        val notes_count : TextView = view.findViewById(R.id.notes_count)
        val notes_toolbar : Toolbar = view.findViewById(R.id.notes_bar)
        val reply_btn : Button = view.findViewById(R.id.replay_btn)
        val mention_btn : Button = view.findViewById(R.id.mention_btn)
        val comment_input : TextView = view.findViewById(R.id.comment_input)
        rv_showData = requireView().findViewById(R.id.comments_list)
        rv_showData.adapter = notesRecyclerView
        val job =lifecycleScope.launchWhenCreated {
            val response:Triple<ArrayList<JsonObject> , ArrayList<String> ,ArrayList<Int>> =notesController.getNotes(post_id=post_id)
            is_loading.visibility  = View.VISIBLE
            val listOfComments : ArrayList<JsonObject> =response.first
            val values : ArrayList<Int> = response.third
            if(values.size > 0) {
                likes_count.text = values[0].toString() + " Likes"
                notes_count.text = values[1].toString() + " Reblogs"
                notes_toolbar.title = (values[0] + values[1] + values[2]).toString() + " Notes"
            }
            notesRecyclerView.setList(listOfComments)
            GlobalScope.launch {
                val listOfImages : ArrayList<String> =response.second
                val listOfBitMaps : ArrayList<Bitmap?> = ArrayList()
                for (url in listOfImages) {
                    listOfBitMaps.add(getImage(url))
                }
                notesRecyclerView.setImages(listOfBitMaps)
            }.join()
            notesRecyclerView.notifyChanges()
            is_loading.visibility  = View.GONE

        }
        val tool_bar : Toolbar = view.findViewById(R.id.notes_bar)
        tool_bar.setOnClickListener {
            it.findNavController().popBackStack()
        }
        mention_btn.setOnClickListener {
            comment_input.append("@")
        }
        reply_btn.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val status : Int =notesController.addReplyCont("Bearer ${localStorage.getTokenData(requireActivity())!!}",post_id ,comment_input.text.toString() )
                if(status == 200)
                {
                    Toast.makeText(activity?.applicationContext , "Note Added Successfully" , Toast.LENGTH_LONG).show()
                    requireActivity().recreate()
                }
                else
                {
                    Toast.makeText(activity?.applicationContext , "Failed to add reply .. try again" , Toast.LENGTH_LONG).show()

                }
                comment_input.text = ""
            }
        }



    }
}