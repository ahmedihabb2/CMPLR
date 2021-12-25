package com.cmp.cmplr.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R

/**
 * This Adapter is responsible for rendering the Notes view
 *
 */

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var comments_list : ArrayList<String> = ArrayList()

    /**
     * Member function used to set the comments data
     *
     * @param comments_list List of comments and users of this comments
     */
    fun setList(comments_list : ArrayList<String>)
    {
        this.comments_list = comments_list
        notifyDataSetChanged()
    }

    /**
     * Inner class used to set the view with real API data
     * Data comming from setList function
     *
     * @param itemView
     */
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

    /**
     * Member function used to know how many views will be rendered
     *
     * @return
     */
    override fun getItemCount(): Int {
        return comments_list.size
    }
}