package com.cmp.cmplr.Model

import android.app.Activity
import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.API.LoginData
import com.cmp.cmplr.API.SignupData
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Mockup.DatabaseMock
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body


/**
 * class LoginModel   class to interface with backendapi
 *
 */
class LoginModel {

    var dataBase = DatabaseMock()
    private var localStorage = LocalStorage()


    /**
     *
     *
     * @param email   mail of the user
     * @param password  password of the user
     * @return boolean, ture if the login is successful , false else
     */
    suspend fun userLogin(@Body signinData : LoginData) : Pair<JsonObject? , Int> {
        var gson : Gson = Gson()
        try {
            val response: Response<JsonObject> = Api_Instance.api.login(signinData)

            if (!response.isSuccessful) {
                return Pair(gson.fromJson(response.errorBody()!!.charStream() , JsonObject::class.java) , response.code())
            }
            return Pair(response.body() , response.code())
        }catch (e: HttpException){
            return Pair(null , 401)
        }
    }


}