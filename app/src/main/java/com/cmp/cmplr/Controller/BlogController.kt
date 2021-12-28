package com.cmp.cmplr.Controller

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

class BlogController {

    suspend fun fetchBlogDataCont(token : String , id :Int) : ArrayList<String>
    {
        var blog_data : ArrayList<String> = ArrayList()
        try {
            val response: Response<JsonObject> = Api_Instance.api.fetchBlogData(token, id)
            if(!response.isSuccessful)
            {
                return blog_data
            }
            val jsonObj : JsonObject? = response.body()?.getAsJsonObject("response")
            val header_img : String = jsonObj?.get("header_image")?.asString ?: ""
            val title : String = jsonObj?.get("title")?.asString ?: ""
            val description : String = jsonObj?.get("description")?.asString ?: ""
            blog_data.add(header_img)
            blog_data.add(title)
            blog_data.add(description)
            return blog_data
        }catch (e : HttpException)
        {
            Log.i("FetchError" , e.toString())
            return  blog_data
        }

    }
}