package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock


/**
 * class LoginModel   class to interface with backendapi
 *
 */
class LoginModel {

    var dataBase = DatabaseMock()


    /**
     *
     *
     * @param email   mail of the user
     * @param password  password of the user
     * @return boolean, ture if the login is successful , false else
     */
    fun isUser(email: String, password: String): Boolean {

        return dataBase.isUser(email, password)
    }


}