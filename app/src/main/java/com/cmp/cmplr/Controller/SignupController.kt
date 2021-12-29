package com.cmp.cmplr.Controller

import com.cmp.cmplr.API.SignupData
import com.cmp.cmplr.Model.SignupModel
import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * class SignupController    class to control the logic of the signup
 *
 */
class SignupController {


    private var signupModel = SignupModel()


    /**
     *
     *
     * @param str   the mail of the user
     * @return boolean   true if the mail is a valid format, false else
     */
    private fun isEmail(str: String): Boolean {
        return str.contains("@")
        //return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }


    /**
     * This function is responsible for validating user input locally
     * If the data is correct it sends it to the server and get the response
     * and send the response back to the view
     *
     * @param signupData A class that contains the signup data (email , blogname , password , age)
     * @return Json that contains server response
     */
    suspend fun validateData(signupData: SignupData): JsonObject {
        val gson = Gson()
        lateinit var jsonResp: JsonObject
        when {
            signupData.blog_name == "" -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":{"blog_name":["Please enter a blog name"]}}""",
                    JsonObject::class.java
                )
            }
            !isEmail(signupData.email) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":{"email":["Please enter a valid email"]}}""",
                    JsonObject::class.java
                )
            }
            (signupData.password == "" || signupData.password.length < 8) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":{"password":["The password must be at least 8 characters"]}}""",
                    JsonObject::class.java
                )

            }
            else -> {

                jsonResp = signupModel.userSignup(signupData)
                    ?: gson.fromJson(
                        """{"meta" :{"status_code" : 422},"error":{"network":["Error occurred while processing the request"]}}""",
                        JsonObject::class.java
                    )

            }
        }
        return jsonResp


    }


}