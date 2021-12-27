package com.cmp.cmplr.Controller


import android.util.Log
import com.cmp.cmplr.Model.NotesModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class NotesController {
    private val notesModel: NotesModel = NotesModel()
    suspend fun getNotes(post_id:Int): Triple<ArrayList<JsonObject>,ArrayList<String>,ArrayList<Int>> {
        val reply_list: ArrayList<JsonObject> = ArrayList()
        val images_list: ArrayList<String> = ArrayList()
        val notes_values : ArrayList<Int> = ArrayList()
        val jsonObj: JsonObject? = notesModel.fetch_notes(post_id)
        if (jsonObj != null) {
            val notes_list: JsonArray = jsonObj.getAsJsonArray("notes")
            val likes_count : Int = jsonObj["total_likes"].asInt
            val reblogs_count : Int= jsonObj["total_reblogs"].asInt
            val replays_count : Int = jsonObj["total_replys"].asInt
            notes_values.add(likes_count)
            notes_values.add(reblogs_count)
            notes_values.add(replays_count)
            for (item in notes_list) {
                images_list.add(item.asJsonObject["avatar"].asString)
                if (item.asJsonObject["type"].asString == "reply") {
                    reply_list.add(item as JsonObject)
                }
            }
        }
        return Triple(reply_list , images_list , notes_values)
    }
    suspend fun addReplyCont(token : String , post_id : Int , content : String) : Int
    {
        return  notesModel.addReply(token, post_id,content)

    }
}