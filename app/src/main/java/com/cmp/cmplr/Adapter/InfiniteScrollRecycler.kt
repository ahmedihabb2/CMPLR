package com.cmp.cmplr.Adapter

import android.app.Activity
import android.content.Intent
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
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.HomeModel
import com.cmp.cmplr.View.Activities.IntroActivity
import com.cmp.cmplr.View.Activities.LoginActivity
import com.cmp.cmplr.View.Activities.WritePostActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.sufficientlysecure.htmltextview.HtmlTextView





class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {

    val tag = "kak"
    var token:String?=""
    var homeModel:HomeModel= HomeModel()
    var wantMorePosts:Boolean=false
    var postList:ArrayList<HomePostData> =ArrayList()
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
        var html_post:HtmlTextView=itemView.findViewById(R.id.html_view_instance )
        var first_hashtag:TextView=itemView.findViewById(R.id.first_hashtag)
        var second_hashtag:TextView=itemView.findViewById(R.id.second_hashtag)
        fun bind(homepost:HomePostData){
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var html:String=homepost.post.content
            html_post.setHtml(html)
            usr_name.text=(homepost.blog.blog_name).toString()
            comments.text=(homepost.post.notes_count).toString()
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

        if (position>=(postList.size-3))
        {
            wantMorePosts=true
        }
        //this place for on click listeners
        holder.usr_img.setOnClickListener{
            var temp:String ="pressed on image of postition:"+position.toString()+" ,array size="+postList.size.toString()
            Log.d("kak",temp)
            val i = Intent(myActivity.applicationContext,IntroActivity::class.java)
            //i.putExtra()
            startActivity(myActivity.applicationContext,i,null)
        }
        Log.d("kak","onbind begin")
    }

    override fun getItemCount(): Int {
        //Log.d("kak","bind begin")
        return postList.size
     }
}


