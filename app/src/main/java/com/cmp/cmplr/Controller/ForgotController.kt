package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.ForgotModel

class ForgotController {
    var forgotModel= ForgotModel()



    fun mailExists(email:String):Boolean{


        return forgotModel.ismailUSed(email);
    }
}