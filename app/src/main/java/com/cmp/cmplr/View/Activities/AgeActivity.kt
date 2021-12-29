package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.R
import com.cmp.cmplr.databinding.AgeScreenBinding


/**
 * This class is responsible for taking age and validating (Terms of service)
 * Then navigating to user info page
 *
 */
class AgeActivity : AppCompatActivity() {
    lateinit var binding: AgeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AgeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ageToolbar.nextAgeScreen.setOnClickListener {
            try {
                if (binding.ageText.text.toString().trim()
                        .toInt() < 18 || binding.ageText.text.toString().trim().toInt() > 80
                ) {
                    Toast.makeText(
                        this.applicationContext,
                        "Age must be between 18 and 80",
                        Toast.LENGTH_LONG
                    )
                        .show()

                } else {
                    val intent = Intent(this, SignupActivity::class.java)
                    intent.putExtra("age", binding.ageText.text.toString().toInt())
                    finish()
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