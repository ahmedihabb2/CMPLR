package com.cmp.cmplr.View.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.BlogController
import com.cmp.cmplr.Controller.BlogPostsController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.R
import com.cmp.cmplr.Shared.getImage

class BlogFragment : Fragment() {
    lateinit var rv_showData: RecyclerView
    val postsRecyclerView: InfiniteScrollRecycler by lazy {
        InfiniteScrollRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.another_user_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localStorage = LocalStorage()
        val blogController = BlogController()
        val blogPostsController = BlogPostsController()
        val blog_name_param: String = arguments?.get("blog_name") as String
        val blog_avatar: String = arguments?.get("blog_avatar") as String
        val blog_id: Int = arguments?.get("blog_id") as Int
        val blog_img: ImageView = view.findViewById(R.id.another_profile_image)
        val blog_cover: ImageView = view.findViewById(R.id.another_cover_image)
        val blog_name: TextView = view.findViewById(R.id.blog_name)
        val description: TextView = view.findViewById(R.id.description)
        val title: TextView = view.findViewById(R.id.blog_name_prof)
        val token: String = localStorage.getTokenData(requireActivity())!!
        title.text = blog_name_param
        rv_showData = requireView().findViewById(R.id.blog_posts)
        var blogName:String?=localStorage.getBlogName(requireActivity())
        postsRecyclerView.putBlogName(blogName)
        postsRecyclerView.putToken(token)
        rv_showData.adapter = postsRecyclerView
        lifecycleScope.launchWhenCreated {
            val blog_data: ArrayList<String> =
                blogController.fetchBlogDataCont("Bearer ${token}", blog_id)
            val img: Bitmap = getImage(blog_avatar)!!
            val header_img: Bitmap = getImage(blog_data[0])!!
            blog_img.setImageBitmap(img)
            blog_cover.setImageBitmap(header_img)
            blog_name.text = blog_data[1]
            Log.i("Blog", blog_data[2])
            if (blog_data[2].isNotEmpty()) {
                description.visibility = View.VISIBLE
                description.text = blog_data[2]
            }
            val posts_list =
                blogPostsController.fetchBlogPostsCont("Bearer $token", blog_name_param)
            postsRecyclerView.updateList(posts_list)
            postsRecyclerView.notifydataSet()
        }


    }
}