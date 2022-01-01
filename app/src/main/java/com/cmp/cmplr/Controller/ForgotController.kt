package com.cmp.cmplr.Controller

import android.util.Log
import com.cmp.cmplr.API.Api_Instance
import com.google.gson.JsonObject
import retrofit2.HttpException
import retrofit2.Response

/**
 * class forgotcontroller
 *  this class responsible for controlling the logic of forgot password view
 *  the class composes the model class responsible for interfacing with the backend api
 *
 */
class ForgotController {

    /**
     *
     *
     * @param email  the email of the forgotten password
     * @return  boolean    whether the mail exist or not
     *
     */
    suspend fun forgotPassword(email: String): Int {
        try {
            val response: Response<JsonObject> = Api_Instance.api.forgotPassword(email)
            Log.i("Forgot", response.headers().toString())
            return response.code()
        } catch (e: HttpException) {
            return 400
        }
    }

}