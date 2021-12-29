package com.cmp.cmplr.Shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.URL


fun getImage(url: String): Bitmap? {
    var inputStream: InputStream? = null
    var bitmap: Bitmap? = null
    var URL: String = url
    try {
        inputStream = URL(URL).openStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        Log.i("Blog", "kYK$e")
    }
    return bitmap
}