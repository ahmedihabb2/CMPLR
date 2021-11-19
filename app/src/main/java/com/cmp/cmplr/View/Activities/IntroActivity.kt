package com.cmp.cmplr.View.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cmp.cmplr.Fragments.*
import com.cmp.cmplr.SharedModules.SliderAdapter
import com.cmp.cmplr.databinding.IntroScreenBinding
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cmp.cmplr.Controller.IntroController
import com.cmp.cmplr.View.*
import com.cmp.cmplr.View.Fragments.*


class IntroActivity : AppCompatActivity(){
    private val fragmentList = ArrayList<Fragment>()
    lateinit var binding : IntroScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroScreenBinding.inflate(layoutInflater)
        val controller : IntroController = IntroController(supportFragmentManager,binding)
        setContentView(binding.root)
        controller.inflateIntroButtons()

        val adapter = SliderAdapter(this)
        val viewPager : ViewPager2 =binding.vpIntro
        viewPager.adapter = adapter

        fragmentList.addAll(listOf(
            Intro1Fragment(), Intro2Fragment(), Intro3Fragment(), Intro4Fragment(), Intro5Fragment()
        ))
        adapter.setFragmentList(fragmentList)


    }


}