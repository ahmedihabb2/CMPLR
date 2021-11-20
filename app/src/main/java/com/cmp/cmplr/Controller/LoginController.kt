package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.LoginModel

/**
 * class LoginController  , this class is responsible for the logic of the login
 *
 */
class LoginController {

    private var loginModel= LoginModel()
    //val databaseMock= DatabaseMock1


    /**
     *
     *
     * @param str  the email of the user
     * @return boolean   whether the email has a valid mail format
     */
    private fun isEmail(str: String): Boolean{
        //return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
        return str.contains("@")
    }


    //fun isEmail(email: String): Boolean {

      //  return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    //}

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
    fun getUserData(email:String,password:String):Int {


        return when {

            !isEmail(email)  -> 1
            password=="" -> 2
            loginModel.isUser(email,password) ->3
            else -> 0

        }
    }
}