package com.cmp.cmplr.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cmp.cmplr.Controller.IntroController
import com.cmp.cmplr.R

class IntroBtnsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_buttons1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Make instance of controller
        val controller: IntroController = IntroController(parentFragmentManager)
        // Get reference to buttons
        val btn: Button = view.findViewById(R.id.login_btn)
        val signup: Button = view.findViewById(R.id.signup_btn)
        // Add listeners to buttons to handle navigations
        btn.setOnClickListener {
            controller.navtoSigninButtons()
        }
        signup.setOnClickListener {
            controller.navToSignupButtons()
        }
    }
}