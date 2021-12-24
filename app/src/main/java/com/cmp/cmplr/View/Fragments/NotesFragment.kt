package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.cmp.cmplr.Adapter.NotesAdapter


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
        val comments_list : ArrayList<String> = ArrayList()
        super.onViewCreated(view, savedInstanceState)
        val mention_btn : Button = view.findViewById(R.id.mention_btn)
        val replay_btn : Button = view.findViewById(R.id.replay_btn)
        val comment_input : TextView = view.findViewById(R.id.comment_input)
        mention_btn.setOnClickListener {
            comment_input.append("@")
        }
        rv_showData = requireView().findViewById(R.id.comments_list)
        rv_showData.adapter = notesRecyclerView
        replay_btn.setOnClickListener {
            if(comment_input.text != "")
            {
                comments_list.add(comment_input.text.toString())
                comment_input.text = ""
                notesRecyclerView.setList(comments_list)
            }
        }


    }
}