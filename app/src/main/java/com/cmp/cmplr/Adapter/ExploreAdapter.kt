package com.cmp.cmplr.Adapter

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.DataClasses.FollowingData
import com.cmp.cmplr.DataClasses.RecommendedData
import com.cmp.cmplr.R

/**
 *  This Class is responsible for rendering the recycler view
 *   - Set it's content
 *   - Refresh it when the content is changed
 *   - Know the number of items to be rendered
 */

class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ItemsViewHolder>() {
    var listOfBlogs : ArrayList<RecommendedData> = ArrayList()
    var listOfImages: ArrayList<Pair<Bitmap, Bitmap>> = ArrayList()

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
     * @param list of  recommended blogs
     */
    fun setData(list : ArrayList<RecommendedData>)
    {
        this.listOfBlogs = list
    }
    /**
     * Member function used to set the view list with data comes from API
     *
     * @param list of  Images
     */
    fun setImages(list : ArrayList<Pair<Bitmap, Bitmap>>)
    {
        this.listOfImages=list
    }

    /**
     * Inner class used to access the view items such as buttons / Images ...etc
     *  And make changes to them
     *
     * @constructor
     * TODO
     *
     * @param itemView
     */

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user_img : ImageView = itemView.findViewById(R.id.check_image)
        val user_blog : TextView = itemView.findViewById(R.id.check_text)
        val user_title : ImageView = itemView.findViewById(R.id.check_cover)
       //val visit_btn : ImageView = itemView.findViewById(R.id.visit_btn)

        fun bind(item : RecommendedData , images:Pair<Bitmap,Bitmap>)
        {
            user_img.setImageBitmap(images.second)
            user_blog.text = item.blog_name
            user_title.setImageBitmap(images.first)
        }

    }

    /**
     * Member function responsible for render each view
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.check_out_blogs, parent, false)
        return ItemsViewHolder(view)
    }

    /**
     * Member function used to know how many items recycler view have
     *
     * @return Int represents the number of items
     */
    override fun getItemCount(): Int {
        return listOfBlogs.size
    }

    /**
     * Member function used to access each item separately in order to
     *  Set click listeners .. etc
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        var item: RecommendedData = listOfBlogs[position]
        var images : Pair<Bitmap,Bitmap> = listOfImages[position]
        holder.bind(item,images)
        holder.user_img.setOnClickListener {
            var blog_data = Bundle()
            blog_data.putString("blog_name",item.blog_name)
            blog_data.putString("blog_avatar", item.avatar)
            blog_data.putInt("blog_id", item.blog_id)
            it.findNavController().navigate(R.id.action_global_blogFragment, blog_data)
        }
    }
}