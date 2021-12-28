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


    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("/api/post/notes")
    suspend fun get_notes(@Query( "post_id" )post_id: Int) : retrofit2.Response<JsonObject>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/user/post/reply")
    suspend fun  reply(@Header("Authorization") token: String?,@Query("post_id")post_id: Int , @Query("reply_text")reply_text : String) : retrofit2.Response<JsonObject>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("/api/posts")
    suspend fun writePost(@Header("Authorization") auth : String, @Body post : PostData) : retrofit2.Response<PostResponse>

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("/api/blog/{id}/info")
    suspend fun fetchBlogData(@Header("Authorization") auth : String,@Path("id") id:Int ): retrofit2.Response<JsonObject>

}