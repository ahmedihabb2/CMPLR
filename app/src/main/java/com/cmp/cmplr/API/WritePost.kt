package com.cmp.cmplr.API


data class PostData(
    val blog_name: String,
    val content: String,
    val source_content: String = "google.com",
    val state: String = "publish",
    val tags: List<String> = listOf(),
    val type: String = "photos"
)

data class PostResponse(
    val meta: Meta,
    val response: ResponseInfo
)

data class Meta(
    val msg: String,
    val status_code: Int
)

data class ResponseInfo(
    val blog: Blog,
    val post: PostInfo
)

data class Blog(
    val avatar: String,
    val avatar_shape: String,
    val blog_id: Int,
    val blog_name: String,
    val follower: Boolean,
    val replies: String
)

data class PostInfo(
    val content: String,
    val date: String,
    val is_liked: Boolean,
    val notes_count: Int,
    val post_id: Int,
    val source_content: String,
    val state: String,
    val tags: List<String>,
    val type: String
)
