package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cmp.cmplr.API.LoginData
import com.cmp.cmplr.API.SignupData
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.LoginController
import com.cmp.cmplr.databinding.LoginBinding
import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * class LoginActivity        class responsible for the login screen
 *
 */
class LoginActivity : AppCompatActivity() {
    private var loginController = LoginController()
    private var localStorage = LocalStorage()
    lateinit var binding: LoginBinding
    private lateinit var signinata: LoginData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarLogin.loginBtn.setOnClickListener {
            closeKeyboard()
            signinata  = LoginData(
             binding.emailText.text.toString().trim(),
             binding.passwordText.text.toString()
            )
            var signinResp: JsonObject
            val job = lifecycleScope.launchWhenCreated  {
                signinResp = loginController.validateSignin(signinata)
                var status_code : Int
                if(signinResp.getAsJsonObject("meta") != null)
                {
                    status_code = signinResp.getAsJsonObject("meta")["status_code"].asInt
                }else
                {
                    status_code = 201
                }
                when(status_code){
                    401 -> {
                        var gson = Gson()
                        var errors = signinResp.getAsJsonObject("error")
                        if(errors["password"] != null)
                        {
                            var list =gson.fromJson(errors["password"].toString(), Array<String>::class.java).asList()
                            binding.passwordText.error = list[0]
                        }
                        if(errors["email"] != null)
                        {
                            var list =gson.fromJson(errors["email"].toString(), Array<String>::class.java).asList()
                            binding.emailText.error = list[0]
                        }
                        if(errors["data"] != null)
                        {
                            var list =gson.fromJson(errors["data"].toString(), Array<String>::class.java).asList()
                            binding.errorText.text = list[0]
                        }
                        if(errors["network"] != null)
                        {
                            var list =gson.fromJson(errors["network"].toString(), Array<String>::class.java).asList()
                            binding.errorText.text = list[0]
                        }
                    }
                    else -> {
                        localStorage.insertTokenData(this@LoginActivity , signinResp["token"].asString)
                        val intent = Intent(this@LoginActivity, MainScreenActivity::class.java)
                        // Make navigation stack empty
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    }
                }

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

