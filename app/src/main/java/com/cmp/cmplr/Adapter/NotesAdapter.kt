package com.cmp.cmplr.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var comments_list : ArrayList<String> = ArrayList()
    fun setList(comments_list : ArrayList<String>)
    {
        this.comments_list = comments_list
        notifyDataSetChanged()
    }
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentView : TextView = itemView.findViewById(R.id.user_comment)
        fun bind(comment : String)
        {
            commentView.text = comment
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout,parent,false)
        return  NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var comment : String = comments_list[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return comments_list.size
    }
}