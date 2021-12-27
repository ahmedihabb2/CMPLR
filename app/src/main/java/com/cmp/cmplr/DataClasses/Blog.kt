package com.cmp.cmplr.DataClasses

data class Blog(
    val avatar: String,    // psoter picture
    val avatar_shape: String,
    val blog_id: Int,
    val blog_name: String,  //usr name
    val follower: Boolean,
    val replies: String
)