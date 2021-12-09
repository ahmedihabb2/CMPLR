package com.cmp.cmplr.Model

import android.app.Activity
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Mockup.DatabaseMock


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
    fun isUser(activity:Activity,email: String, password: String): Boolean {

        val flag : Boolean = dataBase.isUser(email, password)
        if(flag)
        {
            localStorage.insertTokenData(activity, "token")
        }
        return flag
    }


}