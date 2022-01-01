package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.DataClasses.Post
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

/**
 * model class for the hashtag fragment
 */
class HashtagModel {
    var token: String? = ""

    /**
     *
     * function to set the token
     * @param tokenPassed
     */
    fun putToken(tokenPassed: String?) {
        token = tokenPassed
    }

    /**
     * function to call the backend to get the posts in this hashtag
     * @param hashtag the hashtag we want to get its post
     * @return returns a pair, boolean to indicate the success of the back request, and list of post data
     */
    suspend fun listReturn(hashtag: String?): ListBooleanPair {
        var postList: ArrayList<HomePostData> = ArrayList()

        //get the data
        val response: Response<JsonObject>


        var gson: Gson = Gson()
        try {
            //response= Api_Instance.api.homepost("Bearer $token")
            response = Api_Instance.api.hashtagPosts("Bearer $token", hashtag)

            if (!response.isSuccessful) {
                Log.d("back", "not success")
                Log.d("back string", response.toString())

                return ListBooleanPair(postList, false)
            }
            //return Pair(postList,false)
        } catch (e: HttpException) {
            Log.d("back", "http error")
            return ListBooleanPair(postList, false)
        }

        var thebody: JsonObject? = response.body()
        var postsArry = thebody!!.getAsJsonArray("post")

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

                postList.add(HomePostData(blog, post))
            }
        }


        return ListBooleanPair(postList, true)
    }

}