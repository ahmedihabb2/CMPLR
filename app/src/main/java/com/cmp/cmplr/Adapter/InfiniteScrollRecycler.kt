package com.cmp.cmplr.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R
import com.cmp.cmplr.R.drawable
import java.io.IOException
import java.io.InputStream
import java.net.URL
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.Html
import com.cmp.cmplr.DataClasses.HomePostData


class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {
//    init {
//        Log.d("kak", "initblock" )
//
// }
    val tag = "kak"

    var postList:ArrayList<HomePostData> =ArrayList()

    fun setList(posttList:ArrayList<HomePostData> ){
        this.postList=posttList
        notifyDataSetChanged()
        Log.d(tag, "setlist end" )

    }

    class InfiniteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usr_img: ImageView =itemView.findViewById(R.id.user_pic)
        var usr_name:TextView=itemView.findViewById(R.id.username_home)
        var comments:TextView=itemView.findViewById(R.id.comments_btn)
        var html_post:TextView=itemView.findViewById(R.id.html_text_post)
        fun bind(homepost:HomePostData){
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var html:String=homepost.post.content
            html_post.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(html)
            }
            //usr_name.text=(post.name).toString()
            usr_name.text=(homepost.blog.blog_name).toString()
            comments.text=(homepost.post.notes_count).toString()
            var inputStream: InputStream? = null
            var bitmap: Bitmap? = null
            //var URL:String="https://assets.tumblr.com//images//default_avatar//cone_closed_128.png"
            var URL:String=homepost.blog.avatar
            try {
                inputStream = URL(URL).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                usr_img.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                usr_img.setImageResource(R.drawable.kil)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteViewHolder {
        Log.d("kak","oncreate begin")
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.whole_post_view,parent,false)
        Log.d("kak","oncreate begin")
        return InfiniteViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfiniteViewHolder, position: Int) {
        Log.d("kak","onbind begin")
        var post:HomePostData=postList.get(position)
        holder.bind(post)
        holder.usr_img.setOnClickListener{
            var temp:String ="pressed on image of postition:"+position.toString()
            Log.d("kak",temp)

        }
        Log.d("kak","onbind begin")
    }

    override fun getItemCount(): Int {
        //Log.d("kak","bind begin")
        return postList.size
     }
}


