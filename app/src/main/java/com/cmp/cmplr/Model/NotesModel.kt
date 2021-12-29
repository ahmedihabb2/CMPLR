package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException

class NotesModel {

    suspend fun fetch_notes(post_id: Int): JsonObject? {

        try {
            val response = Api_Instance.api.get_notes(post_id)
            if (!response.isSuccessful) {
                Log.i("Notes", response.toString())
                return null
            }
            val jsonObj: JsonObject = response.body()!!
            return jsonObj
        } catch (e: HttpException) {
            Log.i("Notes", e.toString())
            return null
        }

    }

    suspend fun addReply(token: String, post_id: Int, content: String): Int {
        try {
            Log.i("Notes", token)
            val response = Api_Instance.api.reply(token, post_id, content)
            Log.i("Notes", response.toString())
            return response.code()

        } catch (e: HttpException) {
            return 500
        }
    }
}