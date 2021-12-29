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

class HomeModel {


    var token: String? = ""
    fun putToken(tokenPassed: String?) {
        token = tokenPassed
    }


    suspend fun listReturn(): ListBooleanPair {
        var postList: ArrayList<HomePostData> = ArrayList()

        //get the data
        val response: Response<JsonObject>

        var gson: Gson = Gson()
        try {
            response = Api_Instance.api.homepost("Bearer $token")
            //response= Api_Instance.api.homepost_beta()
            if (!response.isSuccessful) {
                Log.d("back", "not success")
                Log.d("back_string", response.toString())

                return ListBooleanPair(postList, false)
            }
            //return Pair(postList,false)
        } catch (e: HttpException) {
            Log.d("back", "http error")
            return ListBooleanPair(postList, false)
        }

        var thebody: JsonObject? = response.body()

        //var jsonlist:List<Char>?=null

        var postsArry = thebody!!.getAsJsonObject("response")
            .getAsJsonArray("post")//["post"].toString().toList()

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

