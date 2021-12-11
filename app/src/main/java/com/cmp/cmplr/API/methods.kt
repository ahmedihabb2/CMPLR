package com.cmp.cmplr.API

import com.google.android.gms.common.api.Response
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.http.*


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
}