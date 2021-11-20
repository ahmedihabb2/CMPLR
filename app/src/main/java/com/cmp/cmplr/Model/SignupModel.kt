package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock


/**
 * class SignupModel  class to interface with the backend api
 *
 */
class SignupModel {


    var dataBase= DatabaseMock()

    /**
     *
     *
     * @param name
     * @param email
     * @param password
     * @return   true if the signup is successful, false else
     */
    fun userSignup(name:String,email: String,password:String):Boolean {

       //todo use the real backend api
        return dataBase.insertUser(name,email,password)
    }

    /**
     *
     *
     * @param email
     * @return  true if the mail is used before, false else
     */
    fun ismailUSed(email:String):Boolean{
        //todo use the real backend api
        return dataBase.emailExist(email)
    }

}