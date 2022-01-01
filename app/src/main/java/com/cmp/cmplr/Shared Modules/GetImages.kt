package com.cmp.cmplr.Shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.URL

/**
 * Function to fetch images from network using image URL
 *
 * @param url
 * @return Bitmap of Image
 */

fun getImage(url: String): Bitmap? {
    var inputStream: InputStream? = null
    var bitmap: Bitmap? = null
    var URL: String = url
    try {
        inputStream = URL(URL).openStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
    }
    return bitmap
}