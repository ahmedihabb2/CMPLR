package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock

class ForgotModel {

    var dataBase= DatabaseMock()


    fun ismailUSed(email:String):Boolean{
        //todo use the real backend api
        return dataBase.emailExist(email)
    }
}