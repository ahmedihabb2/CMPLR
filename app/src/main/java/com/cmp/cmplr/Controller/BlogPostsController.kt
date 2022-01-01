package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.BlogPostsModel
import com.google.gson.Gson

/**
 * This class is responsible for parsing blog posts whether the whole posts
 * or filtered by likes that comes from BlogPostsModel
 * And also for updating the UI with these posts
 */
class BlogPostsController {
    // Initializing object from model
    val blogPostsModel = BlogPostsModel()

    /**
     * Member function responsible for parsing the whole posts into classes
     * And append them to array and send them to view
     * @param token
     * @param blog_name
     * @return Array of Post data
     */
    suspend fun fetchBlogPostsCont(token: String, blog_name: String): ArrayList<HomePostData> {
        val blogPosts: ArrayList<HomePostData> = ArrayList()
        val gson = Gson()
        val postsArry = blogPostsModel.fetchBlogPostsMod(token, blog_name)
        if (postsArry != null) {
            for (item in postsArry) {
                //Using GSON for parsing json object into classes
                val post = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("post"),
                    Post::class.java
                )
                //Using GSON for parsing json object into classes
                val blog = gson.fromJson(
                    item.asJsonObject.getAsJsonObject("blog"),
                    Blog::class.java
                )

                blogPosts.add(HomePostData(blog, post))
            }
        }
        return blogPosts
    }
    /**
     * Member function responsible for parsing the only Liked posts by current user into classes
     * And append them to array and send them to view
     * @param token
     * @return Array of Post data
     */
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