package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.RecommendedData
import com.cmp.cmplr.Model.ExploreModel
import com.google.gson.Gson
import com.google.gson.JsonArray


class RecommendedController {
    val exploreModel = ExploreModel()

    /**
     * function to get the recommended data
     * @param token the token of the user
     *
     * @return list of blogs
     *
     */
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