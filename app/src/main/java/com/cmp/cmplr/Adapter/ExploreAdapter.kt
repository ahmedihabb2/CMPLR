package com.cmp.cmplr.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R


class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ItemsViewHolder>() {
    var images_list: ArrayList<Int> = ArrayList()
    fun setList(images_list: ArrayList<Int>) {
        this.images_list = images_list
        notifyDataSetChanged()
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_View: ImageView = itemView.findViewById(R.id.explore_image)

        fun bind(image: Int) {
            image_View.setImageResource(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.explore_item, parent, false)
        return ItemsViewHolder(view)
    }


    override fun getItemCount(): Int {
        return images_list.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        var image: Int = images_list[position]
        holder.bind(image)
    }
}