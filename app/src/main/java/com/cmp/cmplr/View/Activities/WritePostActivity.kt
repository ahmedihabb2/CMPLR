package com.cmp.cmplr.View.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.R
import com.cmp.cmplr.databinding.ActivityWritePostBinding
import java.util.logging.Level.INFO


class WritePostActivity : AppCompatActivity(),
                          WritePostController.WritePostView {

    private val control = WritePostController
    private lateinit var token : String
    private lateinit var blogName : String
    private lateinit var binding: ActivityWritePostBinding
    private lateinit var imageChooserActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val store = LocalStorage()
        token = store.getTokenData(this)!!
        blogName = store.getBlogName(this)!!

        imageChooserActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imgUri = it.data?.data
                binding.editor.insertImage(imgUri.toString(),"image",150,200)
            }
        }

        initEditor()
        setButtonsEventHandlers()
    }

    override fun getBlogName() : String = blogName
    override fun getPostText() : String = binding.editor.html
    override fun getUserID()   : String = token

    private fun initEditor() {
        binding.editor.setEditorFontColor(Color.WHITE)
        binding.editor.setPlaceholder(getString(R.string.writePostHint))
    }

    private fun setButtonsEventHandlers() {
        binding.postBtn.setOnClickListener {
            val res = control.writePost(this)
            val resAsInt = WritePostController.PostResult.toInt(res)
            finish()
        }

        binding.boldBtn.setOnClickListener {
            binding.editor.setBold()
        }
        binding.italicBtn.setOnClickListener {
            binding.editor.setItalic()
        }
        binding.underlineBtn.setOnClickListener {
            binding.editor.setUnderline()
        }
        binding.strikethroughBtn.setOnClickListener {
            binding.editor.setStrikeThrough()
        }
        binding.subscriptBtn.setOnClickListener {
            binding.editor.setSubscript()
        }
        binding.superscriptBtn.setOnClickListener {
            binding.editor.setSuperscript()
        }
        binding.undoBtn.setOnClickListener {
            binding.editor.undo()
        }
        binding.redoBtn.setOnClickListener {
            binding.editor.redo()
        }
        binding.colorpickerBtn.setOnClickListener {
            val colorpicker = ColorPickerDialog.createColorPickerDialog(this, ColorPickerDialog.DARK_THEME)

            colorpicker.setOnColorPickedListener { color, _ ->
                binding.editor.setEditorFontColor(color)
            }
            colorpicker.show()
        }
        binding.insertImgBtn.setOnClickListener {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            imageChooserActivityLauncher.launch(i)
        }

    }

}