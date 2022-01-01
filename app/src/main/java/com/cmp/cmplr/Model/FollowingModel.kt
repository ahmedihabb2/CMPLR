package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException

/**
 * Class responsible for getting the followers of a user
 */
class FollowingModel {
    suspend fun fetchFollowingMod(token:String) : JsonObject?
    {
        try{
            val response = Api_Instance.api.fetchFollowing(token)
            Log.i("Follow" , response.toString())
            if(!response.isSuccessful)
            {
                return null
            }
            return  response.body()?.getAsJsonObject("response")
        }catch (e : HttpException)
        {
            return null
        }
    }
}