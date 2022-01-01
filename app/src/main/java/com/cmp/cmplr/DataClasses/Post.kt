package com.cmp.cmplr.DataClasses

/**
 * class for data of the psot
 */
data class Post(
    var content: String,
    var date: String,
    var is_liked: Boolean,
    var post_id: Int,
    var source_content: String,
    var state: String,
    var tags: List<String>,
    var type: String,
    var notes_count: Int
)