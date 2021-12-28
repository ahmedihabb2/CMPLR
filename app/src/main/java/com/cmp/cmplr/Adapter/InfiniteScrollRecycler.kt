package com.cmp.cmplr.Adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
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
import android.util.Base64
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import com.cmp.cmplr.Controller.HomeController
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.HomeModel
import com.cmp.cmplr.View.Activities.HashtagPage

//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread




class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {

    val tag = "kak"
    var token:String?=""
    var homeModel:HomeModel= HomeModel()
    var wantMorePosts:Boolean=false
    var postList:ArrayList<HomePostData> =ArrayList()
    var homeController= HomeController()
    lateinit var myActivity:Activity

    fun putActivity(activity: Activity){
        myActivity=activity
    }


    fun putToken(token_passed:String?){
        token=token_passed
        homeModel.putToken(token)
        Log.d("tokenhere",token.toString())
    }
    fun notifydataSet(){
        notifyDataSetChanged()
    }
    fun updateList(posttList:ArrayList<HomePostData> ){
        this.postList+=posttList

        Log.d(tag, "setlist end" )

    }

    class InfiniteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var progressBar:ProgressBar=itemView.findViewById(R.id.progressBar_home)
        var usr_img: ImageView =itemView.findViewById(R.id.user_pic)
        var usr_name:TextView=itemView.findViewById(R.id.username_home)
        var comments:TextView=itemView.findViewById(R.id.comments_btn)
        var html_post:WebView=itemView.findViewById(R.id.html_view_instance )
        var first_hashtag:TextView=itemView.findViewById(R.id.first_hashtag)
        var second_hashtag:TextView=itemView.findViewById(R.id.second_hashtag)
        var notes_btn : TextView = itemView.findViewById(R.id.comments_btn)

        fun bind(homepost:HomePostData){
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            //var temphtml:String="<h2>Alternative text</h2><p>The alt attribute should reflect the image content, so users who cannot see the image gets an understanding of what the image contains:</p><img src=\"https://www.w3schools.com/html/img_chania.jpg\" alt=\"Flowers in Chania\" width=\"460\" height=\"345\">"
            var html:String=homepost.post.content
            //html_post.setHtml(html)
            //html_post.setHtml(html)
            val encodedHtml = Base64.encodeToString(html.toByteArray(), Base64.NO_PADDING)
            html_post.loadData(encodedHtml, "text/html", "base64")
            usr_name.text=(homepost.blog.blog_name).toString()
            comments.text=(homepost.post.notes_count).toString()+" notes"
            first_hashtag.text=""
            second_hashtag.text=""
            if(homepost.post.tags.size==1){
                first_hashtag.text=homepost.post.tags[0]
            }else if (homepost.post.tags.size>1){
                first_hashtag.text="#"+homepost.post.tags[0].replace("\"","")
                second_hashtag.text="#"+homepost.post.tags[1].replace("\"","")

            }



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

    override  fun onBindViewHolder(holder: InfiniteViewHolder, position: Int) {
        Log.d("kak","onbind begin")
        var post:HomePostData=postList.get(position)
        holder.bind(post)

        //this place for on click listeners
        holder.first_hashtag.setOnClickListener{
            val hashtagtextView = it as TextView
            if(hashtagtextView.text.toString()!=""){
                Log.d("lol",hashtagtextView.text.toString())
                var temp:String ="pressed on image of postition:"+position.toString()+" ,array size="+postList.size.toString()
                Log.d("kak",temp)
                var i = Intent(myActivity.applicationContext, HashtagPage::class.java)
                i.putExtra("hashtag",hashtagtextView.text.toString().replace("#","") )
                //i.putExtra()
                startActivity(myActivity.applicationContext,i,null)
            }

        }
        holder.second_hashtag.setOnClickListener{
            //var tempp:TextView=(TextView)it
            val hashtagtextView = it as TextView
            if(hashtagtextView.text.toString()!=""){
                Log.d("lol",hashtagtextView.text.toString())
                var temp:String ="pressed on image of postition:"+position.toString()+" ,array size="+postList.size.toString()
                Log.d("kak",temp)
                var i = Intent(myActivity.applicationContext, HashtagPage::class.java)
                i.putExtra("hashtag",hashtagtextView.text.toString().replace("#","") )
                //i.putExtra()
                //startActivity(i)
                startActivity(myActivity.applicationContext,i,null)
            }

        }


        holder.usr_img.setOnClickListener {
            var blog_data = Bundle()
            blog_data.putString("blog_name" , post.blog.blog_name)
            blog_data.putString("blog_avatar" , post.blog.avatar)
            blog_data.putInt("blog_id" , post.blog.blog_id)
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_blogFragment , blog_data)
        }
        holder.notes_btn.setOnClickListener {
            var data = Bundle()
            data.putInt("post_id" , post.post.post_id)
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_notesFragment, data)
        }
        Log.d("kak","onbind begin")
    }

    override fun getItemCount(): Int {
        //Log.d("kak","bind begin")
        return postList.size
     }
}


