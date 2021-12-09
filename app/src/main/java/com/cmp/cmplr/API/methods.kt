package com.cmp.cmplr.API

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface methods {
    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/login")
    suspend fun login(@Body loginData :LoginData)

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/signup")
    suspend fun signup(@Body signupData :SignupData)

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/forgot_password")
    suspend fun forgotPassword(@Query("Email") email :String)

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/logout")
    suspend fun logout()
}