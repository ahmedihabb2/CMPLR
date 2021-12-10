package com.cmp.cmplr.Controller

import android.app.Activity
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

class SignoutController {
    suspend fun sign_user_out (activity : Activity ,token : String) : Int
    {
        var gson : Gson = Gson()
        try {
            val response: Response<JsonObject> = Api_Instance.api.logout(token)

            return response.code()
        }catch (e: HttpException){
            return 400
        }
    }
}