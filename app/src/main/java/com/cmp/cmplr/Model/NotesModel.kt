package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException

/**
 * Class responsible for fetching the comments of specific post
 *  And also for adding new comment
 *
 */

class NotesModel {
    /**
     * Member function to fetch the notes depending on post_id
     *
     * @param post_id
     * @return JsonObect that contains all comments data
     */
    suspend fun fetch_notes(post_id: Int): JsonObject? {

        try {
            val response = Api_Instance.api.get_notes(post_id)
            if (!response.isSuccessful) {
                return null
            }
            val jsonObj: JsonObject = response.body()!!
            return jsonObj
        } catch (e: HttpException) {
            Log.i("Notes", e.toString())
            return null
        }

    }

    /**
     * Member function to add new comment to the post
     *
     * @param token   the user who wants to add the comment
     * @param post_id the post that user wants to add comment to
     * @param content the content of the comment
     * @return
     */
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