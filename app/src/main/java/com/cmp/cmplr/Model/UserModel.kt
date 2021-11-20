package com.cmp.cmplr.Model


class UserModel(ID: Int) {
    private var posts = ArrayList<String>()

    fun addPost(postTxt: String){
        posts.add(postTxt)
    }

    fun getPosts() : ArrayList<String> {
        return posts
    }
}