package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonArray
import retrofit2.HttpException

/**
 * Class responsible for fetching posts form API whether genral posts
 *  Or filtered by likes
 */
class BlogPostsModel {
    /**
     * Member function to fetch posts in Json format and check if
     * request error occurs and handle it
     * @param token
     * @param blog_name
     * @return  JsonArray that consists of jsonObjects that contains the post data
     */
    suspend fun fetchBlogPostsMod(token: String, blog_name: String): JsonArray? {
        try {
            val response = Api_Instance.api.fetchBlogPosts(token, blog_name)
            if (!response.isSuccessful) {
                Log.i("Blog", response.toString())
                return null
            }
            return response.body()?.getAsJsonObject("response")?.getAsJsonArray("post")
        } catch (e: HttpException) {
            Log.i("Blog", e.toString())
            return null
        }
    }
    /**
     * Member function to fetch Like posts in Json format and check if
     * request error occurs and handle it
     * @param token
     * @return  JsonArray that consists of jsonObjects that contains the post data
     */
    suspend fun fetchLikedPostsMod(token: String): JsonArray? {
        try {
            val response = Api_Instance.api.fetchLikesPosts(token)
            if (!response.isSuccessful) {
                Log.i("Blog", response.toString())
                return null
            }
            return response.body()?.getAsJsonObject("response")?.getAsJsonArray("post")
        } catch (e: HttpException) {
            Log.i("Blog", e.toString())
            return null
        }
    }
}