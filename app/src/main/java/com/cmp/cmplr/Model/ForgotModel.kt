package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock


/**
 * class ForogotModel   class responsible to interface with the backend api
 *
 */
class ForgotModel {

    var dataBase= DatabaseMock()


    /**
     *
     *
     * @param email mail of the user
     * @return boolean   true if the mail is used before, else false
     */
    fun ismailUSed(email:String):Boolean{
        //todo use the real backend api
        return dataBase.emailExist(email)
    }
}