package com.cmp.cmplr.View.Fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cmp.cmplr.Adapter.SliderAdapter
import com.cmp.cmplr.Controller.BlogController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.Controller.SignoutController
import com.cmp.cmplr.R
import com.cmp.cmplr.Shared.getImage
import com.cmp.cmplr.View.Activities.IntroActivity
import com.google.android.material.tabs.TabLayout


class ProfileScreenFragment : Fragment() {
    var tabLayout: TabLayout? = null
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = SliderAdapter(requireActivity())
        val viewPager: ViewPager2 = view.findViewById(R.id.vpBlog)
        viewPager.adapter = adapter
        val localStorage = LocalStorage()
        val settings_btn: Button = view.findViewById(R.id.settings_btn)
        val profile_img: ImageView = view.findViewById(R.id.profile_image)
        val cover_img: ImageView = view.findViewById(R.id.cover_image)
        val profile_title: TextView = view.findViewById(R.id.blog_name)
        val blog_id: Int = localStorage.getBlogID(requireActivity())!!
        val token: String = localStorage.getTokenData(requireActivity())!!
        val blogController = BlogController()
        settings_btn.setOnClickListener {
            val popUpMenu = PopupMenu(activity?.applicationContext, it)
            popUpMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.signout_setting -> {
                        lifecycleScope.launchWhenStarted {
                            val signoutController = SignoutController()
                            val localStorage = LocalStorage()
                            val jsonresp = signoutController.sign_user_out(
                                requireActivity(),
                                "Bearer ${localStorage.getTokenData(requireActivity())!!}"
                            )

                                localStorage.removeToken(requireActivity())
                                Toast.makeText(
                                    activity?.applicationContext,
                                    "Logout Successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(requireActivity(), IntroActivity::class.java)
                                // Make navigation stack empty
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)


                        }
                        true
                    }
                    else -> false
                }
            }
            popUpMenu.inflate(R.menu.settings_menu)
            popUpMenu.show()
        }
        lifecycleScope.launchWhenCreated {
            val blog_data: ArrayList<String> =
                blogController.fetchBlogDataCont("Bearer ${token}", blog_id)
            Log.i("Blog", blog_data.toString())
            val img: Bitmap = getImage(blog_data[3])!!
            val header_img: Bitmap = getImage(blog_data[0])!!
            profile_img.setImageBitmap(img)
            cover_img.setImageBitmap(header_img)
            profile_title.text = blog_data[1]
            Log.i("Blog", blog_data[2])
        }
        viewPager.isUserInputEnabled = false
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        fragmentList.addAll(
            listOf(
                MineBlogFragment(),
                LikedPostsFragment(),
                FollowingFragment(),
            )
        )
        adapter.setFragmentList(fragmentList)


    }
}
