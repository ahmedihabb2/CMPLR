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


class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ItemsViewHolder>() {
    var listOfBlogs : ArrayList<RecommendedData> = ArrayList()
    var listOfImages: ArrayList<Pair<Bitmap, Bitmap>> = ArrayList()

    fun notifyChanges() {
        notifyDataSetChanged()
    }
    fun setData(list : ArrayList<RecommendedData>)
    {
        this.listOfBlogs = list
    }
    fun setImages(list : ArrayList<Pair<Bitmap, Bitmap>>)
    {
        this.listOfImages=list
    }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.check_out_blogs, parent, false)
        return ItemsViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listOfBlogs.size
    }

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