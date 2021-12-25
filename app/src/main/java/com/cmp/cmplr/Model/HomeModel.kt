package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.HomePostData
import com.google.gson.JsonObject
import retrofit2.Response

class HomeModel (){

    private var localStorage = LocalStorage()
    var token:String?=""
    var postList:ArrayList<HomePostData> =ArrayList()
    fun putToken(tokenPassed:String?){
        token=tokenPassed
    }
    suspend fun listReturn():Pair<ArrayList<HomePostData>,Boolean>
    {
        //get the data
        var requestsuccess:Boolean=false
        val response: Response<JsonObject> = Api_Instance.api.homepost(token)
        return Pair(postList,requestsuccess)
    }


}