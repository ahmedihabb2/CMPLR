package com.cmp.cmplr.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.AgeActivity
import com.cmp.cmplr.View.Activities.LoginActivity
import com.cmp.cmplr.View.Activities.SignupActivity

class SignupBtnsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signup_email : Button = view.findViewById(R.id.signup_email)
        signup_email.setOnClickListener {
            val intent = Intent(activity?.applicationContext, AgeActivity::class.java)
            startActivity(intent)
        }
    }
}

