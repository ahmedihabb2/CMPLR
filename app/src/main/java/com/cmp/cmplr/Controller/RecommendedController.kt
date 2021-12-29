package com.cmp.cmplr.Controller

import com.cmp.cmplr.API.RecommendedData
import com.cmp.cmplr.Model.ExploreModel
import com.google.gson.Gson
import com.google.gson.JsonArray

class RecommendedController {
    val exploreModel = ExploreModel()
    suspend fun fetchRecommendedCont(token: String): ArrayList<RecommendedData> {
        val gson = Gson()
        val listOfBlogs: ArrayList<RecommendedData> = ArrayList()
        val jsonObj = exploreModel.fetchRecommendedModel(token)
        if (jsonObj != null) {
            val jsonArr: JsonArray = jsonObj.getAsJsonObject("response").getAsJsonArray("blogs")
            for (blog in jsonArr) {
                listOfBlogs.add(
                    gson.fromJson(
                        blog.asJsonObject,
                        RecommendedData::class.java
                    )
                )
            }
        }
        return listOfBlogs
    }
}