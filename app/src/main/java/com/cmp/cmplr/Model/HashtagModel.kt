package com.cmp.cmplr.Model

import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.google.gson.JsonObject
import retrofit2.Response

class HashtagModel {


    suspend fun listReturn(): ListBooleanPair{
        var postList:ArrayList<HomePostData> =ArrayList()

        //get the data
        val response: Response<JsonObject>











        return ListBooleanPair(postList,true)
    }

}