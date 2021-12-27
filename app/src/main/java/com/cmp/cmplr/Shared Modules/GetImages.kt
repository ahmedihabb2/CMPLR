package com.cmp.cmplr.Shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.cmp.cmplr.R
import java.io.IOException
import java.io.InputStream
import java.net.URL


fun getImage(url : String) : Bitmap?
{
    var inputStream: InputStream? = null
    var bitmap: Bitmap? = null
    var URL:String= url
    try {
        inputStream = URL(URL).openStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return bitmap
}