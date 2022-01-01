package com.cmp.cmplr.Adapter

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.google.gson.JsonObject


/**
 *  This Class is responsible for rendering the recycler view
 *   - Set it's content
 *   - Refresh it when the content is changed
 *   - Know the number of items to be rendered
 */

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var comments_list: ArrayList<JsonObject> = ArrayList()
    var images_list: ArrayList<Bitmap?> = ArrayList()

    /**
     * Member function used to notify the view that the data is changed
     *
     */
    fun notifyChanges() {
        notifyDataSetChanged()
    }
    /**
     * Member function used to set the view list with data comes from API
     *
     * @param list of  Images
     */
    fun setImages(images_list: ArrayList<Bitmap?>) {
        this.images_list = images_list
    }
    /**
     * Member function used to set the view list with data comes from API (Comments & users)
     *
     * @param list of  recommended blogs
     */
    fun setList(comments_list: ArrayList<JsonObject>) {
        this.comments_list = comments_list
    }

    /**
     * Inner class used to set the view with real API data
     * Data coming from setList & set Images functions
     *
     * @param itemView
     */
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentView: TextView = itemView.findViewById(R.id.user_comment)
        var user_name: TextView = itemView.findViewById(R.id.notes_user_name)
        var user_img: ImageView = itemView.findViewById(R.id.comment_user_img)
        fun bind(comment: JsonObject, img: Bitmap?) {
            user_img.setImageBitmap(img)
            if(comment["content"].toString() != "null") {
                commentView.text = comment["content"].asString
            }else
            {
                commentView.text = "empty comment"
            }
            user_name.text = comment["blog_name"].asString
        }

    }
    /**
     * Member function responsible for render each view
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return NoteViewHolder(view)
    }
    /**
     * Member function used to access each item separately in order to
     *  Set click listeners .. etc
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var comment: JsonObject = comments_list[position]
        var img: Bitmap? = images_list[position]
        holder.bind(comment, img)
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