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
import kotlin.contracts.contract

class HashtagModel {


    suspend fun listReturn(hashtag:String?): ListBooleanPair{
        var postList:ArrayList<HomePostData> =ArrayList()

        //get the data
        val response: Response<JsonObject>


        var gson : Gson = Gson()
        try {
            //response= Api_Instance.api.homepost("Bearer $token")
            response= Api_Instance.api.hashtagPosts(hashtag)

            if (!response.isSuccessful) {
                Log.d("back","not success")
                Log.d("back string",response.toString())

                return ListBooleanPair(postList,false)
            }
            //return Pair(postList,false)
        }catch (e: HttpException){
            Log.d("back","http error")
            return ListBooleanPair(postList,false)
        }

        var thebody: JsonObject? =response.body()
        var jsonlist= thebody!!.getAsJsonArray("post")
            Log.d("hashtag", "After json")
            Log.d("hashtag", "size= "+jsonlist.size().toString())
            Log.d("hashtag", "tag= "+hashtag)

        for (item in jsonlist){
            var single_post=item.asJsonObject.getAsJsonObject("post")
            var single_blog=item.asJsonObject.getAsJsonObject("blog")

            val content: String=single_post["content"].toString().replace("\"","")
            Log.d("hashtag", content.toString())
            val date: String=single_post["date"].toString()
            val is_liked: Boolean=single_post["is_liked"].toString().toBoolean()
            Log.d("back_content",is_liked.toString())
            val post_id: Int=single_post["post_id"].toString().toInt()
            val source_content: String=single_post["source_content"].toString()
            val state: String=single_post["state"].toString()
            val type: String=single_post["type"].toString()
            //val tags = MutableList<String>()
            //var tags = listOf("df","sdf")
            var tagsString=single_post["tags"].toString()
            tagsString=tagsString.replace("[","")
            tagsString=tagsString.replace("]","")
            var tags=tagsString.split(",")
            val notes_count:Int=single_post["notes_count"].toString().toInt()

            val post_data: Post = Post(content,date,is_liked,post_id,source_content,state,tags,type,notes_count)
            //Log.d("back_content",)
            val blog_data: Blog = Blog(single_blog["avatar"].toString().replace("\"",""),single_blog["avatar_shape"].toString(),single_blog["blog_id"].toString().toInt(),
                single_blog["blog_name"].toString().replace("\"",""),single_blog["follower"].toString().toBoolean(),
                single_blog["replies"].toString())
            var homePostData:HomePostData= HomePostData(blog_data,post_data)
            postList.add(homePostData)



        }





        return ListBooleanPair(postList,true)
    }

}