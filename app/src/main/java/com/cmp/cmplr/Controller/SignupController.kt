package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.SignupModel

class SignupController {


    private var signupModel = SignupModel()


    private fun isEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }

    fun getSignupData(name:String,email:String,password:String):Int{

      var return_value:Int=0

        when{
            name=="" -> return_value=0
            !isEmail(email)->return_value=1
            signupModel.ismailUSed(email)-> return_value=2
            password==""->return_value=3
            else -> {
                signupModel.userSignup(name,email,password)
                return_value=4
            }
        }

        return return_value


    }



}