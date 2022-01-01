package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.LoginData
import com.cmp.cmplr.Model.LoginModel
import com.google.gson.Gson
import com.google.gson.JsonObject

/**
 * class LoginController  , this class is responsible for the logic of the login
 *
 */
class LoginController {

    private var loginModel = LoginModel()

    /**
     *
     *
     * @param str  the email of the user
     * @return boolean   whether the email has a valid mail format
     */
    fun isEmail(str: String): Boolean {
        if (str.contains(" ") || !str.contains("@")) {
            return false
        }
        return true
    }


    /**
     * This function is responsible for validating the login data locally first and then
     * Passes the data to the backend to check it this user exist or not
     *
     * @param signinData class object contain user data
     * @return JsonObject contain the error is exists
     */
    suspend fun validateSignin(signinData: LoginData): JsonObject {
        val gson = Gson()
        lateinit var jsonResp: JsonObject
        when {
            !isEmail(signinData.email) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 401},"error":{"email":["Invalid email"]}}""",
                    JsonObject::class.java
                )
            }
            (signinData.password == "" || signinData.password.length < 8) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 401},"error":{"password":["Invalid password"]}}""",
                    JsonObject::class.java
                )
            }
            else -> {

                var resp: Pair<JsonObject?, Int> = loginModel.userLogin(signinData)
                if (resp.second == 401) {
                    jsonResp = gson.fromJson(
                        """{"meta" :{"status_code" : 401},"error":{"data":["Either your login email or password is incorrect"]}}""",
                        JsonObject::class.java
                    )
                } else {
                    jsonResp = resp.first!!

                }

            }
        }
        return jsonResp
    }
}