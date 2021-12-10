package com.cmp.cmplr.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api_Instance {
    val api : methods by lazy {
        Retrofit.Builder()
            .baseUrl("http://13.68.206.72/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(methods::class.java)
    }
}