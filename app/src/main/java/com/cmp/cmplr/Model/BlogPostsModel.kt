package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonArray
import retrofit2.HttpException

class BlogPostsModel {

    suspend fun fetchBlogPostsMod(blog_name: String) : JsonArray? {
        try{
            val response = Api_Instance.api.fetchBlogPosts(blog_name)
            if(!response.isSuccessful)
            {
                Log.i("Blog" , response.toString())
                return  null
            }
            return response.body()?.getAsJsonObject("response")?.getAsJsonArray("post")
        }catch (e : HttpException)
        {
            Log.i("Blog" , e.toString())
            return null
        }
    }
}