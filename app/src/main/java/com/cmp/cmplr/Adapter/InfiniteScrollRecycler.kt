package com.cmp.cmplr.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R
import com.cmp.cmplr.R.drawable

class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {
//    init {
//        Log.d("kak", "initblock" )
//
// }
    val tag = "kak"

    var postList:ArrayList<UserPost> =ArrayList()

    fun setList(posttList:ArrayList<UserPost> ){
        this.postList=posttList
        notifyDataSetChanged()
        Log.d(tag, "setlist end" )

    }

    class InfiniteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usr_img: ImageView =itemView.findViewById(R.id.user_pic)
        var usr_name:TextView=itemView.findViewById(R.id.username_home)
        var notes_btn : TextView = itemView.findViewById(R.id.comments_btn)

        fun bind(post:UserPost){
            Log.d("kak","bind begin")
            usr_name.text=(post.name).toString()
            usr_img.setImageResource(R.drawable.kil)
            Log.d("kak","bind end")

            //usr_img.setImageResource((post.picture))
        }
        //item


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteViewHolder {
        Log.d("kak","oncreate begin")
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.whole_post_view,parent,false)
        Log.d("kak","oncreate begin")
        return InfiniteViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfiniteViewHolder, position: Int) {
        Log.d("kak","onbind begin")
        var post:UserPost=postList.get(position)
        holder.bind(post)
        holder.usr_img.setOnClickListener{
            var temp:String ="pressed on image of postition:"+position.toString()
            Log.d("kak",temp)

        }
        holder.notes_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_notesFragment)

        }
        Log.d("kak","onbind begin")
    }

    override fun getItemCount(): Int {
        //Log.d("kak","bind begin")
        return postList.size
     }
}


