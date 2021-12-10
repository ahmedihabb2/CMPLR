package com.cmp.cmplr.API

import com.google.android.gms.common.api.Response
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface methods {
    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/login")
    suspend fun login(@Body loginData :LoginData)

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/register/insert")
    suspend fun signup(@Body signupData :SignupData) : retrofit2.Response<JsonObject>


    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/forgot_password")
    suspend fun forgotPassword(@Query("Email") email :String)

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/logout")
    suspend fun logout()
}