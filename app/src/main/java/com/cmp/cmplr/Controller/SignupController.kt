package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.SignupModel

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
    fun getSignupData(name: String, email: String, password: String): Int {

        var return_value: Int = 0

        when {
            name == "" -> return_value = 0
            !isEmail(email) -> return_value = 1
            (password == "" || password.length < 6) -> return_value = 2
            else -> {
                signupModel.userSignup(name, email, password)
                return_value = 3
            }
        }

        return return_value


    }


}