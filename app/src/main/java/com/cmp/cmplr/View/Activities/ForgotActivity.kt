package com.cmp.cmplr.View.Activities

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cmp.cmplr.Controller.ForgotController
import com.cmp.cmplr.databinding.ForgotPasswordBinding


/**
 * class ForgotActivity , class responsible for the view of the forgot screen
 *
 */
class ForgotActivity : AppCompatActivity() {

    private var forgotController = ForgotController()
    lateinit var binding: ForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.forgotToolbar.forgotSubmitButton.setOnClickListener {

            closeKeyboard()
            var email: String = binding.emailText.text.toString()
            val job = lifecycleScope.launchWhenCreated {
                val status: Int = forgotController.forgotPassword(email)
                Log.i("Forgot", status.toString())
                if (status == 422) {
                    Toast.makeText(this@ForgotActivity, "Invalid email format", Toast.LENGTH_LONG)
                        .show()
                } else if (status == 404) {
                    Toast.makeText(
                        this@ForgotActivity,
                        "There is no account associated with this email",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (status == 500) {
                    Toast.makeText(this@ForgotActivity, "Server error", Toast.LENGTH_LONG).show()
                } else if (status == 200) {
                    Toast.makeText(
                        this@ForgotActivity,
                        "The email has been sent",
                        Toast.LENGTH_LONG
                    ).show()

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