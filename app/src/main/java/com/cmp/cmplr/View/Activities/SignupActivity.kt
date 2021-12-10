package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cmp.cmplr.API.SignupData
import com.cmp.cmplr.API.SignupResp
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.SignupController
import com.cmp.cmplr.databinding.SignupBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import org.json.JSONObject


/**
 * class SignupActivity  class responsible for the signup screen
 *
 */
class SignupActivity : AppCompatActivity() {

    private var signupController = SignupController()
    private var localStorage = LocalStorage()
    lateinit var binding: SignupBinding
    private lateinit var signupData: SignupData

    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras
        super.onCreate(savedInstanceState)
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupToolbar.signupButton.setOnClickListener {
            closeKeyboard()
            //Get input fields data
            signupData = SignupData(
                binding.emailTextSignup.text.toString() ,
                binding.passwordTextSignup.text.toString(),
                binding.nameTextSignup.text.toString(),
                extras!!.getInt("age")
            )
            // Send data to controller
            var signupResp: JsonObject
            val job = lifecycleScope.launchWhenCreated  {
                signupResp = signupController.validateData(signupData)
                when(signupResp.getAsJsonObject("meta")["status_code"].asInt){
                    201 -> {
                        binding.errorTextSignup.text = ""
                        // Save user token locally
                        localStorage.insertTokenData(this@SignupActivity , signupResp.getAsJsonObject("response")["token"].asString)
                        val intent = Intent(this@SignupActivity, MainScreenActivity::class.java)
                        // Make navigation stack empty
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    else -> {
                        var gson = Gson()
                        var error : String = ""
                        var list =gson.fromJson(signupResp["error"].toString(), Array<String>::class.java).asList()
                        list.forEach{
                            error += "∘ $it\n"
                        }
                        binding.errorTextSignup.text = error
                    }
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