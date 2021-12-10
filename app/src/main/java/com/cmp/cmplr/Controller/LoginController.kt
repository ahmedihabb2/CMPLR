package com.cmp.cmplr.Controller

import android.app.Activity
import com.cmp.cmplr.API.LoginData
import com.cmp.cmplr.API.SignupData
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
    private fun isEmail(str: String): Boolean {
        //return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
        return str.contains("@")
    }


    /**
     *
     *
     * @param email  email wanted to login
     * @param password  password wanted to login
     * @return an integer and according to it the view acts
     *   1-> the email is invalid mail fromat
     *   2-> password is empty
     *   3-> right login
     *   4-> password or mail is invalid
     */
    suspend fun validateSignin(signinData: LoginData): JsonObject {
        val gson = Gson()
        lateinit var jsonResp: JsonObject
        when {
            !isEmail(signinData.email) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 401},"error":["Please enter a valid email"]}""",
                    JsonObject::class.java
                )
            }
            (signinData.password == "" || signinData.password.length < 6) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 401},"error":["Invalid password"]}""",
                    JsonObject::class.java
                )

            }
            else -> {

                jsonResp = loginModel.userLogin(signinData)
                    ?: gson.fromJson(
                        """{"meta" :{"status_code" : 401},"error":["Error occurred while processing the request"]}""",
                        JsonObject::class.java
                    )

            }
        }
        return jsonResp
    }
}