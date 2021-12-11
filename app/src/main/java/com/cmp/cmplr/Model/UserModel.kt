package com.cmp.cmplr.Model

/**
 * Mocking user posts using array
 *
 * @constructor
 *
 *
 * @param ID
 */
class UserModel(ID: Int) {
    private var posts = ArrayList<String>()

    fun addPost(postTxt: String) {
        posts.add(postTxt)
    }

    fun getPosts(): ArrayList<String> {
        return posts
    }
}