package com.cmp.cmplr.View.Fragments

import android.graphics.Bitmap
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.cmp.cmplr.Adapter.NotesAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
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
        val notesController : NotesController = NotesController()
        val is_loading : ProgressBar = view.findViewById(R.id.comments_load)
        val likes_count : TextView = view.findViewById(R.id.likes_count)
        val notes_count : TextView = view.findViewById(R.id.notes_count)
        val notes_toolbar : Toolbar = view.findViewById(R.id.notes_bar)
        lifecycleScope.launchWhenCreated {
            val response:Triple<ArrayList<JsonObject> , ArrayList<String> ,ArrayList<Int>> =notesController.getNotes("10")
            is_loading.visibility  = View.VISIBLE
            val listOfComments : ArrayList<JsonObject> =response.first
            val values : ArrayList<Int> = response.third
            likes_count.text = values[0].toString()+" Likes"
            notes_count.text = values[1].toString()+" Reblogs"
            notes_toolbar.title = (values[0]+values[1]+values[2]).toString() + " Notes"
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
        super.onViewCreated(view, savedInstanceState)
        val mention_btn : Button = view.findViewById(R.id.mention_btn)
        val replay_btn : Button = view.findViewById(R.id.replay_btn)
        val comment_input : TextView = view.findViewById(R.id.comment_input)
        mention_btn.setOnClickListener {
            comment_input.append("@")
        }
        rv_showData = requireView().findViewById(R.id.comments_list)
        rv_showData.adapter = notesRecyclerView
//        replay_btn.setOnClickListener {
//            if(comment_input.text != "")
//            {
//                comments_list.add(comment_input.text.toString())
//                comment_input.text = ""
//                notesRecyclerView.setList(comments_list)
//            }
//        }


    }
}