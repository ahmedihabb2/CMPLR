package com.cmp.cmplr.View

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.LoginController
import com.cmp.cmplr.databinding.LoginBinding


class LoginActivity : AppCompatActivity(){


    //fun View.hideKeyboard() {
      //  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
       // imm.hideSoftInputFromWindow(windowToken, 0)
    //}
    private var loginController = LoginController()
    lateinit var binding: LoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarLogin.loginBtn.setOnClickListener {
            closeKeyboard()

            var email=binding.emailText.text.toString()
            var password=binding.passwordText.text.toString()

            //Toast.makeText(applicationContext,email+ password,Toast.LENGTH_SHORT).show()
            binding.errorText
            when(loginController.getUserData(email, password)) {
                1->binding.errorText.text = "please enter a valid mail"
                2->binding.errorText.text = "please enter a password"
                //TODO navigate to the next screen after login
                3 ->{
                    Toast.makeText(applicationContext,"Logged In",Toast.LENGTH_SHORT).show()
                    binding.errorText.text = ""
                }
                0->binding.errorText.text = "invalid email or password, try again or press on forgot my password"


            }

        }
        binding.showPass.setOnClickListener {
            if(binding.showPass.isChecked){
                //Toast.makeText(applicationContext,"Checked",Toast.LENGTH_SHORT).show()
                //binding.editTextPassword

                binding.passwordText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                //Toast.makeText(applicationContext,"NOT checked",Toast.LENGTH_SHORT).show()
                binding.passwordText.transformationMethod = PasswordTransformationMethod.getInstance()

            }
        }

        binding.forgotButton.setOnClickListener{

            //TODO navigate to forgot screen
            Toast.makeText(applicationContext,"navigate to forgot screen",Toast.LENGTH_SHORT).show()
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

