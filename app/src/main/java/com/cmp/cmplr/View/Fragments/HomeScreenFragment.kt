package com.cmp.cmplr.View.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.SignoutController
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.IntroActivity
import com.cmp.cmplr.View.Activities.WritePostRequester


class HomeScreenFragment : Fragment(),
    WritePostRequester {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.writePostBtn.setOnClickListener {
//            Toast.makeText(activity?.applicationContext, "teessst", Toast.LENGTH_SHORT).show()
//            (activity as WritePostButtonEventHandler).onWritePostClicked(this)
//        }
        val signoutBtn : Button = view.findViewById(R.id.signout_btn)
        val notesBtn : Button = view.findViewById(R.id.notes_btn)
        signoutBtn.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val signoutController : SignoutController = SignoutController()
                val localStorage : LocalStorage= LocalStorage()
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
        }
        notesBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeScreenFragment_to_notesFragment)
        }
    }

    override fun onPostRequestDone(result: WritePostController.PostResult) {
        TODO("Not yet implemented")
    }


}