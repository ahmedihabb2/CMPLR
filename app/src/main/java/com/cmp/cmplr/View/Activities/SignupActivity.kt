package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.SignupController
import com.cmp.cmplr.databinding.SignupBinding


/**
 * class SignupActivity  class responsible for the signup screen
 *
 */
class SignupActivity : AppCompatActivity() {

    private var signupController = SignupController()

    lateinit var binding: SignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupToolbar.signupButton.setOnClickListener {
            closeKeyboard()
            var name: String = binding.nameTextSignup.text.toString()
            var email: String = binding.emailTextSignup.text.toString()
            var password: String = binding.passwordTextSignup.text.toString()
            var cond = signupController.getSignupData(name, email, password)
            when (cond) {

                0 -> {
                    binding.errorTextSignup.text = "Please enter a name"
                }
                1 -> {
                    binding.errorTextSignup.text = "Please enter a valid mail"
                }
                2 -> {
                    binding.errorTextSignup.text = "Password must be more than 6 characters"
                }
                3 -> {
                    binding.errorTextSignup.text = ""
                    // Save user token locally

                    val intent = Intent(this, MainScreenActivity::class.java)
                    // Make navigation stack empty
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                }
            }

        }

        binding.loginButtonSignup.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)

        }
        binding.showPassSignup.setOnClickListener {
            if (binding.showPassSignup.isChecked) {
                binding.passwordTextSignup.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.passwordTextSignup.transformationMethod =
                    PasswordTransformationMethod.getInstance()

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