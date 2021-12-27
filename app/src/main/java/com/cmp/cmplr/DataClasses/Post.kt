package com.cmp.cmplr.DataClasses

data class Post(
    val content: String,
    val date: String,
    val is_liked: Boolean,
    val post_id: Int,
    val source_content: String,
    val state: String,
    val tags: List<String>,
    val type: String,
    val notes_count:Int
)