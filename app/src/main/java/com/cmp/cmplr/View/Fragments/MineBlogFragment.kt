package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.BlogPostsController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.R

class MineBlogFragment : Fragment() {
    lateinit var rv_showData : RecyclerView
    val postsRecyclerView : InfiniteScrollRecycler by lazy {
        InfiniteScrollRecycler()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mineblogfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localStorage = LocalStorage()
        val blogPostsController = BlogPostsController()
        val token : String = localStorage.getTokenData(requireActivity())!!
        rv_showData = requireView().findViewById(R.id.mine_blog_posts)
        postsRecyclerView.putToken(token)
        rv_showData.adapter = postsRecyclerView
        lifecycleScope.launchWhenCreated {
            val posts_list = blogPostsController.fetchBlogPostsCont("Bearer $token",localStorage.getBlogName(requireActivity())!!)
            postsRecyclerView.updateList(posts_list)
            postsRecyclerView.notifydataSet()
        }

    }
}