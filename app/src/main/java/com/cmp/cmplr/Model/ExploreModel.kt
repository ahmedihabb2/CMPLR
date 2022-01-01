package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException

/**
 * Class responsible for fetching All of Explore Page data
 *
 */
class ExploreModel {
    /**
     * Member function to fetch Recommended blogs
     *
     * @param token
     * @return JsonObject that contains all of the blogs data
     */
    suspend fun fetchRecommendedModel(token: String): JsonObject? {
        try {
            val response = Api_Instance.api.fetchrecommendedBlogs(token)
            if (!response.isSuccessful) {
                return null
            }
            return response.body()
        } catch (e: HttpException) {
            return null
        }
    }
}