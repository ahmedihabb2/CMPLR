package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.API.SignupData
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body


/**
 * class SignupModel  class to interface with the backend api
 *
 */
class SignupModel {
    /**
     * This function is responsible for making API request to sign user up
     *
     * @param signupData A Class object that contains user data (email , blogname , password , age)
     * @return Json object contains API response
     */
    suspend fun userSignup(@Body signupData: SignupData): JsonObject? {
        var gson: Gson = Gson()
        try {
            val response: Response<JsonObject> = Api_Instance.api.signup(signupData)

            if (!response.isSuccessful) {
                return gson.fromJson(response.errorBody()!!.charStream(), JsonObject::class.java)
            }
            return response.body()
        } catch (e: HttpException) {
            return null
        }
    }


}