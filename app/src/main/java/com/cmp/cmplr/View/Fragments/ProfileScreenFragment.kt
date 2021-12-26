package com.cmp.cmplr.View.Fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cmp.cmplr.R

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.SignoutController
import com.cmp.cmplr.View.Activities.IntroActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Job


class ProfileScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localStorage = LocalStorage()
        val settings_btn : Button = view.findViewById(R.id.settings_btn)
        settings_btn.setOnClickListener {
            val popUpMenu  = PopupMenu(activity?.applicationContext,it)
            popUpMenu.setOnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.signout_setting ->{
                        lifecycleScope.launchWhenStarted {
                            val signoutController  = SignoutController()
                            val localStorage = LocalStorage()
                            val jsonresp =signoutController.sign_user_out(requireActivity(),"Bearer ${localStorage.getTokenData(requireActivity())!!}")
                            if(jsonresp == 200)
                            {
                                localStorage.removeToken(requireActivity())
                                Toast.makeText(activity?.applicationContext ,"Logout Successfully" , Toast.LENGTH_LONG).show()
                                val intent = Intent(requireActivity(), IntroActivity::class.java)
                                // Make navigation stack empty
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }else
                            {
                                Toast.makeText(activity?.applicationContext ,"Unauthorized" , Toast.LENGTH_LONG).show()

                            }
                        }
                        true
                    }
                    else -> false
                }
            }
            popUpMenu.inflate(R.menu.settings_menu)
            popUpMenu.show()
            }
    }
}
