package com.cmp.cmplr.Model

import com.cmp.cmplr.Mockup.DatabaseMock

class SignupModel {


    var dataBase= DatabaseMock()

    fun userSignup(name:String,email: String,password:String):Boolean {

       //todo use the real backend api
        return dataBase.insertUser(name,email,password)
    }
    fun ismailUSed(email:String):Boolean{
        //todo use the real backend api
        return dataBase.emailExist(email)
    }

}