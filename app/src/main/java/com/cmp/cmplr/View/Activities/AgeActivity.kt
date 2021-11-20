package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.R

import com.cmp.cmplr.databinding.FragmentAgeSignUpBinding

class AgeActivity : AppCompatActivity() {
    lateinit var binding: FragmentAgeSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAgeSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupBtn.setOnClickListener {
            try {
                if (binding.ageField.text.toString().toInt() < 13) {
                    Toast.makeText(
                        this.applicationContext,
                        getString(R.string.errorMsgIfAgeIsLessThan13),
                        Toast.LENGTH_LONG
                    )
                        .show()

                    //findNavController().navigate(R.id.action_ageSignUpFragment_to_chooseAgeSignUpOrLoginFragment)
                } else {
                    Toast.makeText(
                        this.applicationContext,
                        getString(R.string.successMsgIfAgeIs13),
                        Toast.LENGTH_LONG
                    )
                        .show()

                    val intent = Intent(this, SignupActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this.applicationContext,
                    getString(R.string.errorMsgIfInvalidNumberFormat),
                    Toast.LENGTH_LONG
                )
            }
        }

    }
}