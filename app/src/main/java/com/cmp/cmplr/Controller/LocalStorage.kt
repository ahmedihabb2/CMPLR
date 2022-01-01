package com.cmp.cmplr.Controller

import android.app.Activity
import android.content.Context

val fileName: String = "SharedData"

/**
 * This class is responsible for storing and retrieving data to and from LocalStorage
 *  Using SharedPref
 */
class LocalStorage {
    /**
     * Member function used to Storing the token and blog name of the user
     *
     * @param activity the calling activity
     * @param data  the token
     * @param blogName
     */
    fun insertTokenData(activity: Activity, data: String, blogName: String) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("token", data)
            putString("blog", blogName)
            apply()
        }

    }

    /**
     * Member function used to Storing the BlogID
     *
     * @param activity
     * @param blog_id
     */
    fun insertBlogID(activity: Activity, blog_id: Int) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt("blog_id", blog_id)
            apply()
        }
    }

    /**
     * Retrieving  Data from local storage
     *
     * @param activity
     * @return
     */
    fun getTokenData(activity: Activity): String? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString("token", "")
    }
    /**
     * Retrieving  Data from local storage
     *
     * @param activity
     * @return
     */
    fun getBlogName(activity: Activity): String? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString("blog", "")
    }
    /**
     * Retrieving  Data from local storage
     *
     * @param activity
     * @return
     */
    fun getBlogID(activity: Activity): Int? {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getInt("blog_id", 0)
    }
    /**
     * Delete all data from the storage
     *
     * @param activity
     * @return
     */
    fun removeToken(activity: Activity) {
        val sharedPref = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        sharedPref.edit().remove("token").apply()
        sharedPref.edit().remove("blog").apply()
        sharedPref.edit().remove("blog_id").apply()
    }
}