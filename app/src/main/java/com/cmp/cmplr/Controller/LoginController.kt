package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.LoginModel


class LoginController {

    private var loginModel= LoginModel()
    //val databaseMock= DatabaseMock1

    private fun isEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }


    //fun isEmail(email: String): Boolean {

      //  return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    //}

    fun getUserData(email:String,password:String):Int {


        return when {

            !isEmail(email)  -> 1
            password=="" -> 2
            loginModel.isUser(email,password) ->3
            else -> 0

        }
    }
}