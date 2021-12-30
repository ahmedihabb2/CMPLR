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
import com.cmp.cmplr.R


class FollowingAdapter: RecyclerView.Adapter<FollowingAdapter.FollowHolder>()  {
    var listOfFollowing : ArrayList<FollowingData> = ArrayList()
    var listOfImages: ArrayList<Pair<Bitmap , Bitmap>> = ArrayList()

    fun notifyChanges() {
        notifyDataSetChanged()
    }
    fun setData(list : ArrayList<FollowingData>)
    {
        Log.i("Follow" , list.toString())
        this.listOfFollowing = list
    }
    fun setImages(list : ArrayList<Pair<Bitmap , Bitmap>>)
    {
        this.listOfImages=list
    }
    class FollowHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val user_img : ImageView = itemView.findViewById(R.id.following_img)
        val user_blog : TextView = itemView.findViewById(R.id.following_blog)
        val user_title : TextView = itemView.findViewById(R.id.following_title)
        val visit_btn : ImageView = itemView.findViewById(R.id.visit_btn)
        fun bind(item : FollowingData , images:Pair<Bitmap,Bitmap>)
        {
            Log.i("Follow" , item.toString())
            user_img.setImageBitmap(images.second)
            user_blog.text = item.blog_name
            user_title.text = item.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.following, parent, false)
        return FollowHolder(view)
    }

    override fun onBindViewHolder(holder: FollowHolder, position: Int) {
        var item : FollowingData= listOfFollowing[position]
        var images : Pair<Bitmap,Bitmap> = listOfImages[position]
        holder.visit_btn.setOnClickListener {
            var blog_data = Bundle()
            blog_data.putString("blog_name",item.blog_name)
            blog_data.putString("blog_avatar", item.avatar)
            blog_data.putInt("blog_id", item.blog_id)
            it.findNavController().navigate(R.id.action_global_blogFragment, blog_data)
        }
        holder.bind(item , images)
    }

    /**
     * Member function used to know how many views will be rendered
     *
     * @return
     */
    override fun getItemCount(): Int {
        return listOfFollowing.size
    }
}