package com.cmp.cmplr.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cmp.cmplr.Fragments.LoginBtnsFragment
import com.cmp.cmplr.Fragments.SignupBtnsFragment
import com.cmp.cmplr.R

class IntroBtnsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_buttons1 , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn : Button = view.findViewById(R.id.login_btn)
        val signup : Button = view.findViewById(R.id.signup_btn)
        btn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
                .replace(R.id.intro_buttons, LoginBtnsFragment()).addToBackStack(null).commit()
        }
        signup.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
                .replace(R.id.intro_buttons, SignupBtnsFragment()).addToBackStack(null).commit()
        }
    }
}