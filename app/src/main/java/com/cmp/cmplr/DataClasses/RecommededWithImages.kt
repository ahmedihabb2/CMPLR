package com.cmp.cmplr.DataClasses

import android.graphics.Bitmap


data class RecommededWithImages(
    val avatar: Bitmap?,
    val avatar_shape: String,
    val background_color: String,
    val blog_id: Int,
    val blog_name: String,
    val description: String,
    val header_image: Bitmap?,
    val last_update: String,
    val title: String,
    val url: String
)