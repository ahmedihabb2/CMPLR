package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.LoginController
import com.cmp.cmplr.databinding.LoginBinding


/**
 * class LoginActivity        class responsible for the login screen
 *
 */
class LoginActivity : AppCompatActivity() {
    private var loginController = LoginController()
    private var localStorage = LocalStorage()
    lateinit var binding: LoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarLogin.loginBtn.setOnClickListener {
            closeKeyboard()

            var email = binding.emailText.text.toString()
            var password = binding.passwordText.text.toString()
            binding.errorText
            when (loginController.getUserData(this,email, password)) {
                1 -> binding.errorText.text = "please enter a valid mail"
                2 -> binding.errorText.text = "please enter a password"
                3 -> {
                    val intent = Intent(this, MainScreenActivity::class.java)
                    // Make navigation stack empty
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    binding.errorText.text = ""
                }
                0 -> binding.errorText.text =
                    "invalid email or password, try again or press on forgot my password"
            }

        }
        binding.showPass.setOnClickListener {
            if (binding.showPass.isChecked) {
                binding.passwordText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.passwordText.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }

        binding.forgotButton.setOnClickListener {

            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
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

