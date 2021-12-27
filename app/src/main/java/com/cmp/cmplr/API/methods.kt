package com.cmp.cmplr.API

import com.google.android.gms.common.api.Response
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.http.*

/**
 * Interface that contains All API methods
 *
 */

interface methods {
    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/login")
    suspend fun login(@Body loginData :LoginData) : retrofit2.Response<JsonObject>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/register/insert")
    suspend fun signup(@Body signupData :SignupData) : retrofit2.Response<JsonObject>


    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/forgot_password")
    suspend fun forgotPassword(@Body email :String) : retrofit2.Response<JsonObject>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/logout")
    suspend fun logout(@Header("Authorization") token: String) : retrofit2.Response<JsonObject>


    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("/api/user/dashboard")
    suspend fun homepost(@Header("Authorization") token: String?) : retrofit2.Response<JsonObject>

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("/api/user/dashboard")
    suspend fun homepost_beta() : retrofit2.Response<JsonObject>

    //Used for mocking Notes page
    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/v3/7b25a4ab-fc6a-4e55-a405-e99c8a78f730/")
    suspend fun get_notes(@Body post_id : String) : retrofit2.Response<JsonArray>
}