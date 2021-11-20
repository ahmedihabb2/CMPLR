package com.cmp.cmplr.View.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cmp.cmplr.Controller.IntroController
import com.cmp.cmplr.SharedModules.SliderAdapter
import com.cmp.cmplr.View.Fragments.*
import com.cmp.cmplr.databinding.IntroScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * Intro Activity class used to inflate the xml file related to the intro screens
 * also it responsible for initializing the Slider Adapter with needed Fragments
 *
 */

class IntroActivity : AppCompatActivity() {
    private val fragmentList = ArrayList<Fragment>()
    lateinit var binding: IntroScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroScreenBinding.inflate(layoutInflater)
        // Create instance of intro controller
        val controller = IntroController(supportFragmentManager, binding)
        setContentView(binding.root)
        // Inflate intro buttons
        controller.inflateIntroButtons()
        // Initializing the Slider Adapter
        val adapter = SliderAdapter(this)
        val viewPager: ViewPager2 = binding.vpIntro
        viewPager.adapter = adapter
        fragmentList.addAll(
            listOf(
                Intro1Fragment(),
                Intro2Fragment(),
                Intro3Fragment(),
                Intro4Fragment(),
                Intro5Fragment()
            )
        )
        adapter.setFragmentList(fragmentList)


    }


}