package com.cmp.cmplr.Model

import android.app.Activity
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Mockup.DatabaseMock


/**
 * class SignupModel  class to interface with the backend api
 *
 */
class SignupModel {

    private var localStorage = LocalStorage()
    var dataBase = DatabaseMock()

    /**
     *
     *
     * @param name
     * @param email
     * @param password
     * @return   true if the signup is successful, false else
     */
    fun userSignup(activity:Activity,name: String, email: String, password: String): Boolean {

        // TODO:: Make backend request
        val flag : Boolean = dataBase.insertUser(name, email, password)
        if(flag) {
            localStorage.insertTokenData(activity, "token")
        }
        return flag
    }

    /**
     *
     *
     * @param email
     * @return  true if the mail is used before, false else
     */
    fun ismailUSed(email: String): Boolean {
        // TODO:: Make backend request 2
        return dataBase.emailExist(email)
    }

}