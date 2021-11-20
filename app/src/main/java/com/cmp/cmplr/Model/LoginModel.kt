package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock

class LoginModel {

    var dataBase=DatabaseMock()

    fun isUser(email: String,password:String):Boolean {

        return dataBase.isUser(email,password)
    }


}