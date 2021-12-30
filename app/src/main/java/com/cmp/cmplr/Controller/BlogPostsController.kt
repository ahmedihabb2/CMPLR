package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.BlogPostsModel
import com.google.gson.Gson

class BlogPostsController {
    val blogPostsModel = BlogPostsModel()

    suspend fun fetchBlogPostsCont(token: String, blog_name: String): ArrayList<HomePostData> {
        val blogPosts: ArrayList<HomePostData> = ArrayList()
        val gson = Gson()
        val postsArry = blogPostsModel.fetchBlogPostsMod(token, blog_name)
        if (postsArry != null) {
            for (item in postsArry) {
                val post = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("post"),
                    Post::class.java
                )
                val blog = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("blog"),
                    Blog::class.java
                )

                blogPosts.add(HomePostData(blog, post))
            }
        }
        return blogPosts
    }
    suspend fun fetchLikedPostsCont(token: String): ArrayList<HomePostData> {
        val blogPosts: ArrayList<HomePostData> = ArrayList()
        val gson = Gson()
        val postsArry = blogPostsModel.fetchLikedPostsMod(token)
        if (postsArry != null) {
            for (item in postsArry) {
                val post = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("post"),
                    Post::class.java
                )
                val blog = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("blog"),
                    Blog::class.java
                )

                blogPosts.add(HomePostData(blog, post))
            }
        }
        return blogPosts
    }

}