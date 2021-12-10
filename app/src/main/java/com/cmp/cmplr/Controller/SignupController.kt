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
     *
     *
     * @param name  the name of the new user
     * @param email     the mail of the new user
     * @param password     the password of the new user
     * @return integer which indicates what the view should do
     *              1-> name is empty
     *              2-> the mail has invalid format
     *              3-> the mail is used before
     *              4-> signup successful
     */
    suspend fun validateData(signupData: SignupData): JsonObject {
        val gson = Gson()
        lateinit var jsonResp: JsonObject
        when {
            signupData.blog_name == "" -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":["Please enter a blog name"]}""",
                    JsonObject::class.java
                )
            }
            !isEmail(signupData.email) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":["Please enter a valid email"]}""",
                    JsonObject::class.java
                )
            }
            (signupData.password == "" || signupData.password.length < 6) -> {
                jsonResp = gson.fromJson(
                    """{"meta" :{"status_code" : 422},"error":["Password must be more than 6 characters"]}""",
                    JsonObject::class.java
                )

            }
            else -> {

                jsonResp = signupModel.userSignup(signupData)
                    ?: gson.fromJson(
                        """{"meta" :{"status_code" : 422},"error":["Error occurred while processing the request"]}""",
                        JsonObject::class.java
                    )

            }
        }
        return jsonResp


    }


}