package com.cmp.cmplr.Model

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.API.SignupData
import com.cmp.cmplr.API.SignupResp
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Mockup.DatabaseMock
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body
import java.io.IOException
import okhttp3.internal.http2.ErrorCode
import org.json.JSONObject
import java.util.*


/**
 * class SignupModel  class to interface with the backend api
 *
 */
class SignupModel {

    var dataBase = DatabaseMock()

    /**
     *
     *
     * @param name
     * @param email
     * @param password
     * @return   true if the signup is successful, false else
     */
    suspend fun userSignup(@Body signupData : SignupData) : JsonObject? {
        var gson : Gson = Gson()
        try {
            val response: Response<JsonObject> = Api_Instance.api.signup(signupData)

            if (!response.isSuccessful) {
                return gson.fromJson(response.errorBody()!!.charStream() , JsonObject::class.java)
            }
            return response.body()
        }catch (e:HttpException){
            return null
        }
    }


}