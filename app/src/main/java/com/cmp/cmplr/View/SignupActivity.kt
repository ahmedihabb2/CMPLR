package com.cmp.cmplr.View

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.SignupController
import com.cmp.cmplr.databinding.SignupBinding

class SignupActivity : AppCompatActivity() {

    private var signupController= SignupController()
    lateinit var binding: SignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.signupToolbar.signupButton.setOnClickListener{

            closeKeyboard()
            //Toast.makeText(applicationContext,"done",Toast.LENGTH_SHORT).show()

            //var name1:String=binding.nameText.toString()
            var name:String=binding.nameTextSignup.text.toString()
            //var email1:String=binding.emailText.toString()
            var email:String=binding.emailTextSignup.text.toString()
            //var password:String=binding.passwordText.toString()
            var password:String=binding.passwordTextSignup.text.toString()
            //Toast.makeText(applicationContext,name+email+password,Toast.LENGTH_SHORT).show()

            var cond=signupController.getSignupData(name, email, password)
            //Toast.makeText(applicationContext,cond.toString(),Toast.LENGTH_SHORT).show()
            //Toast.makeText(applicationContext,email,Toast.LENGTH_SHORT).show()

            when(cond){

                0->{
                    binding.errorTextSignup.text="please enter a name"
                }
                1->{
                    binding.errorTextSignup.text="please enter a valid mail"
                }
                2->{
                    binding.errorTextSignup.text="this mail is used for another account"
                }
                3->{
                    binding.errorTextSignup.text="please enter a password"
                }
                4->{
                    binding.errorTextSignup.text=""
                    Toast.makeText(applicationContext,"signedup",Toast.LENGTH_SHORT).show()
                    //TODO navigate to the needed screen , remove toast

                }
            }

        }

        binding.loginButtonSignup.setOnClickListener{
            Toast.makeText(applicationContext,"login",Toast.LENGTH_SHORT).show()
            //todo navigate to login

        }
        binding.showPassSignup.setOnClickListener {
            if(binding.showPassSignup.isChecked){
                //Toast.makeText(applicationContext,"Checked",Toast.LENGTH_SHORT).show()
                //binding.editTextPassword

                binding.passwordTextSignup.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                //Toast.makeText(applicationContext,"NOT checked",Toast.LENGTH_SHORT).show()
                binding.passwordTextSignup.transformationMethod = PasswordTransformationMethod.getInstance()

            }
        }




        // fun View.hideKeyboard() {
        //   val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //  imm.hideSoftInputFromWindow(windowToken, 0)
        //}

    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    
    

}