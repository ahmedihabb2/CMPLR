package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

class NotesModel {

    suspend fun fetch_notes(post_id : String) : JsonObject?{

        try{
            val response = Api_Instance.api.get_notes(post_id)
            if (!response.isSuccessful) {
                Log.i("Notes" , response.toString())
                return null
            }
            val jsonObj : JsonObject = response.body()!![0] as JsonObject
            return  jsonObj
        }catch (e: HttpException)
        {
            Log.i("Notes" , e.toString())
            return null
        }

    }
}