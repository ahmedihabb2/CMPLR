package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.RecommendedData
import com.cmp.cmplr.Model.ExploreModel
import com.google.gson.Gson
import com.google.gson.JsonArray

/**
 * Class for handling Explore page view and it's data
 *
 */
class RecommendedController {
    val exploreModel = ExploreModel()

    /**
     * Member function used to parse the recommended blogs data that comes from Model
     *
     * @param token
     * @return A List of RecommendedData class objects that contains the blog data
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