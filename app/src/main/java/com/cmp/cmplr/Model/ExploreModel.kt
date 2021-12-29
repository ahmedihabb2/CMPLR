package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException

class ExploreModel {

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