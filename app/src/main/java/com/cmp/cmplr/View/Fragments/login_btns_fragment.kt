package com.cmp.cmplr.Fragments

import android.R.attr
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cmp.cmplr.Controller.IntroController
import com.cmp.cmplr.R
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.getkeepsafe.relinker.ReLinker.log
import android.R.attr.data
import android.content.ContentValues.TAG

import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginBtnsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var  controller : IntroController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        controller = IntroController(parentFragmentManager)
        val mGoogleSignInClient=controller.configureGoogleClient(requireActivity())
        val loginGoogle : Button = view.findViewById(R.id.login_google)
        loginGoogle.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if(result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                controller.firebaseAuthWithGoogle(account.idToken!! ,auth ,requireActivity())
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }

    }


}
