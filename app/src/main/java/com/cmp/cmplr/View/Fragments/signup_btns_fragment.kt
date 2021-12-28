package com.cmp.cmplr.Fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.cmp.cmplr.Controller.IntroController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.AgeActivity
import com.cmp.cmplr.View.Activities.MainScreenActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupBtnsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var controller: IntroController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        controller = IntroController(parentFragmentManager)
        val mGoogleSignInClient = controller.configureGoogleClient(requireActivity())
        val signup_email: Button = view.findViewById(R.id.signup_email)
        val signup_google: Button = view.findViewById(R.id.signup_google)
        signup_email.setOnClickListener {
            val intent = Intent(activity?.applicationContext, AgeActivity::class.java)
            startActivity(intent)
        }
        signup_google.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            var localStorage = LocalStorage()
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                    val done = controller.firebaseAuthWithGoogle(
                        account.idToken!!,
                        auth,
                        requireActivity()
                    )

                    Toast.makeText(
                        activity?.applicationContext,
                        "email ${account.email} username ${account.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    localStorage.insertTokenData(requireActivity(), account.idToken, " ")
                    val intent =
                        Intent(activity?.applicationContext, MainScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                } catch (e: ApiException) {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Error occured",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Google Sign In failed, update UI appropriately
                    Log.w(ContentValues.TAG, "Google sign up failed", e)
                }
            }

        }
}

