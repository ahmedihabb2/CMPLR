package com.cmp.cmplr.View

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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


        binding.forgotToolbar.forgotSubmitButton.setOnClickListener{

            closeKeyboard()
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
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}