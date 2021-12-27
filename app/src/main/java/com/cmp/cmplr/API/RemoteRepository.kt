package com.cmp.cmplr.API

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RemoteRepository {




    suspend fun homepost(@Header("Authorization") token: String?) : retrofit2.Response<JsonObject>

}