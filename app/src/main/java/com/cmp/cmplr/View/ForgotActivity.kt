package com.cmp.cmplr.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.ForgotController
import com.cmp.cmplr.databinding.ForgotPasswordBinding

class ForgotActivity: AppCompatActivity() {

    private var forgotController= ForgotController()
    lateinit var binding:ForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle? ){
        super.onCreate(savedInstanceState)
        binding=ForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*binding.forgotToolbar.forgotSubmitButton.setOnClickListener{


            var email:String = binding.emailText.text.toString()
            when(forgotController.mailExists(email)){

                true->{
                    //todo navigate to the next screen
                    binding.errorTextForgot.text=""

                    Toast.makeText(applicationContext,"mail sent",Toast.LENGTH_SHORT).show()


                }
                false->{
                    binding.errorTextForgot.text="please enter an existing mail"
                }
            }
        }*/
    }


}