package com.cmp.cmplr.Controller


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cmp.cmplr.Fragments.LoginBtnsFragment
import com.cmp.cmplr.Fragments.SignupBtnsFragment
import com.cmp.cmplr.R
import com.cmp.cmplr.View.IntroBtnsFragment
import com.cmp.cmplr.databinding.IntroScreenBinding


class IntroController(private val fragmentManager : FragmentManager , val binding : IntroScreenBinding? = null) {

    fun navtoSigninButtons()
    {
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
            .replace(R.id.intro_buttons, LoginBtnsFragment()).addToBackStack(null).commit()
    }

    fun navToSignupButtons()
    {
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
            .replace(R.id.intro_buttons, SignupBtnsFragment()).addToBackStack(null).commit()
    }

    fun inflateIntroButtons()
    {
        val fragment: Fragment = IntroBtnsFragment()
        val fm: FragmentManager = fragmentManager
        val transaction: FragmentTransaction = fm.beginTransaction()
        transaction.replace(binding!!.introButtons.id, fragment)
        transaction.commit()
    }
}