package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.DataClasses.LoginData
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Mockup.DatabaseMock
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body


/**
 * class Responsible for signing user in the application
 *
 */
class LoginModel {

    var dataBase = DatabaseMock()
    private var localStorage = LocalStorage()


    /**
     *  Member function to send the user data to backend and get the response
     *
     * @param signinData Class object that contains the username and password
     * @return
     */
    suspend fun userLogin(@Body signinData: LoginData): Pair<JsonObject?, Int> {
        var gson: Gson = Gson()
        try {
            val response: Response<JsonObject> = Api_Instance.api.login(signinData)

            if (!response.isSuccessful) {
                return Pair(
                    gson.fromJson(
                        response.errorBody()!!.charStream(),
                        JsonObject::class.java
                    ), response.code()
                )
            }
            return Pair(response.body(), response.code())
        } catch (e: HttpException) {
            return Pair(null, 401)
        }
    }


}