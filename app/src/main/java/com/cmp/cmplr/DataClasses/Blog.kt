package com.cmp.cmplr.DataClasses

/**
 * Struct to represent blog info, displayed in profile screen
 */
data class Blog(
    var avatar: String,    // psoter picture
    var avatar_shape: String,
    var blog_id: Int,
    var blog_name: String,  //usr name
    var follower: Boolean,
    var replies: String
)