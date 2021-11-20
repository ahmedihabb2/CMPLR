package com.cmp.cmplr.Controller


import android.app.Activity
import android.content.ContentValues
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cmp.cmplr.Fragments.LoginBtnsFragment
import com.cmp.cmplr.Fragments.SignupBtnsFragment
import com.cmp.cmplr.R
import com.cmp.cmplr.View.IntroBtnsFragment
import com.cmp.cmplr.databinding.IntroScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Intro Controller class for handling any logic that related to into screens
 *
 * @property fragmentManager Property responsible for performing actions on fragments
 * @property binding binding object of into activity to access it's elements
 */

class IntroController(private val fragmentManager : FragmentManager , val binding : IntroScreenBinding? = null) {
    /**
     *  Navigate from login ang signup buttons to login buttons
     *
     */
    fun navtoSigninButtons()
    {
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
            .replace(R.id.intro_buttons, LoginBtnsFragment()).addToBackStack(null).commit()
    }

    /**
     * Navigate from login ang signup buttons to signup buttons
     *
     */
    fun navToSignupButtons()
    {
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
            .replace(R.id.intro_buttons, SignupBtnsFragment()).addToBackStack(null).commit()
    }

    /**
     * inflates the intro buttons to the intro screen framelayout
     *
     */
    fun inflateIntroButtons()
    {
        val fragment: Fragment = IntroBtnsFragment()
        val fm: FragmentManager = fragmentManager
        val transaction: FragmentTransaction = fm.beginTransaction()
        transaction.replace(binding!!.introButtons.id, fragment)
        transaction.commit()
    }

    /**
     * A function to configure google client in order to  use
     * Sign in with google
     * @param fa  The Activity that contains the button
     * @return Google Client
     */

    fun configureGoogleClient(fa: Activity): GoogleSignInClient {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("476166821478-hlrv6pgdg4q18tl76t52v12273t53a41.apps.googleusercontent.com")
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso
        return GoogleSignIn.getClient(fa, gso)
    }

    /**
     * This function is responsible to add the user to firebase
     * After google client successfully gets the user data and token
     * @param idToken  user token
     * @param auth     auth instance of firebase
     * @param activity The Activity that contains the button
     */
    fun firebaseAuthWithGoogle(idToken: String , auth : FirebaseAuth , activity: Activity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("Signin", "signInWithCredential:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("Signin", "signInWithCredential:failure", task.exception)
                }
            }
    }


}