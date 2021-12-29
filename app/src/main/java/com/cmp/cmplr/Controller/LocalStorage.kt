package com.cmp.cmplr.Controller

import android.app.Activity
import android.content.Context

val fileName: String = "SharedData"

class LocalStorage() {

    fun insertTokenData(activity: Activity, data: String, blogName: String) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("token", data)
            putString("blog", blogName)
            apply()
        }

    }

    fun insertBlogID(activity: Activity, blog_id: Int) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt("blog_id", blog_id)
            apply()
        }
    }

    fun getTokenData(activity: Activity): String? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString("token", "")
    }

    fun getBlogName(activity: Activity): String? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString("blog", "")
    }

    fun getBlogID(activity: Activity): Int? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getInt("blog_id", 0)
    }

    fun removeToken(activity: Activity) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        sharedPref.edit().remove("token").apply()
        sharedPref.edit().remove("blog").apply()
        sharedPref.edit().remove("blog_id").apply()
    }
}