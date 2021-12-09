package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.ForgotModel

/**
 * class forgotcontroller
 *  this class responsible for controlling the logic of forgot password view
 *  the class composes the model class responsible for interfacing with the backend api
 *
 */
class ForgotController {


    var forgotModel = ForgotModel()


    /**
     *
     *
     * @param email  the email of the forgotten password
     * @return  boolean    whether the mail exist or not
     *
     */
    fun mailExists(email: String): Boolean {


        return forgotModel.ismailUSed(email);
    }

}