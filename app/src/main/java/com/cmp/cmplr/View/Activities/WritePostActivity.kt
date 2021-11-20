package com.cmp.cmplr.View.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.R
import com.cmp.cmplr.databinding.ActivityWritePostBinding


class WritePostActivity : AppCompatActivity(),
    WritePostController.WritePostView {
    private val control = WritePostController
    lateinit var binding: ActivityWritePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postBtn.setOnClickListener {
            WritePostController.writePost(this)
            finish()
        }
    }

    override fun getPostText() = binding.editTextTextMultiLine.text.toString()
    override fun getUserID() = intent.getIntExtra(getString(R.string.user_ID_Intent_search_key), -1)
}