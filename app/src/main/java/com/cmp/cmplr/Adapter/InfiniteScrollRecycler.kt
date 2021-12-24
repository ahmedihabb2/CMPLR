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
import androidx.activity.result.contract.ActivityResultContracts
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.HomeModel
import org.sufficientlysecure.htmltextview.HtmlTextView


class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {
//    init {
//        Log.d("kak", "initblock" )
//
// }
    val tag = "kak"
    var token:String?=""
    var homeModel:HomeModel= HomeModel()

    var postList:ArrayList<HomePostData> =ArrayList()
    fun putToken(token_passed:String?){
        token=token_passed
        homeModel.putToken(token)
        Log.d("tokenhere",token.toString())
    }
    fun notifydataSet(){
        notifyDataSetChanged()
    }
    fun setList(posttList:ArrayList<HomePostData> ){
        this.postList+=posttList

        Log.d(tag, "setlist end" )

    }

    class InfiniteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usr_img: ImageView =itemView.findViewById(R.id.user_pic)
        var usr_name:TextView=itemView.findViewById(R.id.username_home)
        var comments:TextView=itemView.findViewById(R.id.comments_btn)
        var html_post:HtmlTextView=itemView.findViewById(R.id.html_view_instance )
        fun bind(homepost:HomePostData){
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var html:String=homepost.post.content
            html_post.setHtml(html)
//            html_post.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
//            } else {
//                Html.fromHtml(html)
//            }
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

        if (position>=(postList.size-2))
        {   var postsList_new: ArrayList<HomePostData> = ArrayList()
            for (i in 1 ..5){
                val blog: Blog = Blog("https://assets.tumblr.com//images//default_avatar//cone_closed_128.png",
                    "r",1, "theUsrName", true,"everyone");
                //val content: String="this post for <b>test update and edit</b> for HADIDY KAK khaled"
                val content: String="<h1>Queen say only yesterday you deserved to be.</h1><p>Molestiae pariatur ut rerum. Est expedita molestias qui. Quas voluptatibus dignissimos nobis est assumenda minima tempora.</p><p>Praesentium quia laudantium qui ut voluptates. Quidem consectetur molestiae occaecati saepe ipsam dolor ducimus. Eum natus consequatur labore a officiis odio reprehenderit. Labore nostrum est cumque labore.</p><p>Autem ut sunt esse aut nihil. Id facilis assumenda explicabo eius ut et. Molestiae delectus et quo et. Nulla fugiat in iste consequatur.</p>"
                val date: String="Friday, 24-Dec-21 15:35:30 UTC"
                val is_liked: Boolean=false
                val post_id: Int=54
                val source_content: String="google.com"
                val state: String="publish"
                val type: String="photos"
                //val tags = MutableList<String>()
                val tags = listOf("summer", "winter", "Evans")
                val notes_count:Int=99
                val post_data: Post = Post(content,date,is_liked,post_id,source_content,state,tags,type,notes_count)

                var post:HomePostData= HomePostData(blog,post_data)
                postsList_new.add(post)
            }

            setList(postsList_new)
        }
        //this place for on click listeners
        holder.usr_img.setOnClickListener{
            var temp:String ="pressed on image of postition:"+position.toString()+" ,array size="+postList.size.toString()
            Log.d("kak",temp)

        }
        Log.d("kak","onbind begin")
    }

    override fun getItemCount(): Int {
        //Log.d("kak","bind begin")
        return postList.size
     }
}


