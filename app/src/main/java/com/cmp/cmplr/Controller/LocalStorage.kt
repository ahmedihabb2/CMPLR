package com.cmp.cmplr.Controller

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
val fileName : String = "SharedData"
class LocalStorage (){

    fun insertTokenData(activity:Activity, data:String, blogName: String ) {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)?: return
        with (sharedPref.edit()) {
            putString("token", data)
            putString("blog", blogName)
            apply()
        }

    }
    fun getTokenData(activity:Activity) : String?
    {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        return sharedPref.getString("token","")
    }

    fun getBlogName(activity:Activity) : String?
    {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        return sharedPref.getString("blog","")
    }

    fun removeToken(activity:Activity) {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        sharedPref.edit().remove("token").apply()
        sharedPref.edit().remove("blog").apply()
    }
}