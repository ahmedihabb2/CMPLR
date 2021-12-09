package com.cmp.cmplr.Controller

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class LocalStorage {
    val fileName : String = "SharedData"
    fun insertTokenData(activity:Activity,data:String ) {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)?: return
        with (sharedPref.edit()) {
            putString("token", data)
            apply()
        }

    }
    fun getTokenData(activity:Activity) : String?
    {
        val sharedPref = activity?.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        return sharedPref.getString("token","")
    }
}