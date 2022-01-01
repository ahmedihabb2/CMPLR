package com.cmp.cmplr.Controller

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

/**
 * This Class is responsible for handling the meta data of the blog not the posts
 *
 */

class BlogController {
    /**
     * Function responsible for fetching the data from API and Parsing it into desired format
     *
     * @param token The token of the user
     * @param id  The ID of the blog that user wants to visit or it's own Blog
     * @return Array of Stings that contains the URLs of Header and avatar and it's name & title & description
     */
    suspend fun fetchBlogDataCont(token: String, id: Int): ArrayList<String> {
        var blog_data: ArrayList<String> = ArrayList()
        try {
            val response: Response<JsonObject> = Api_Instance.api.fetchBlogData(token, id)
            Log.i("Blog", response.toString())
            if (!response.isSuccessful) {
                return blog_data
            }
            val jsonObj: JsonObject? = response.body()?.getAsJsonObject("response")
            //Extracting the useful info from the whole json object
            val header_img: String = jsonObj?.get("header_image")?.asString ?: ""
            val title: String = jsonObj?.get("title")?.asString ?: ""
            val description: String = jsonObj?.get("description")?.asString ?: ""
            val avatar: String = jsonObj?.get("avatar")?.asString ?: ""
            blog_data.add(header_img)
            blog_data.add(title)
            blog_data.add(description)
            blog_data.add(avatar)
            return blog_data
        } catch (e: HttpException) {
            Log.i("FetchError", e.toString())
            return blog_data
        }

    }
}