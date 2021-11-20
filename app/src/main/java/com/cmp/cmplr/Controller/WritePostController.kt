package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.managers.UserModelManager

object WritePostController  {
    enum class PostResult { SUCCESS, FALIURE_EMPTY_CONTENT}

    interface WritePostView {
        fun getPostText() : String
        fun getUserID() : Int
    }

    fun writePost(v : WritePostView) : PostResult {
        val txt = v.getPostText()
        if(txt.isNullOrEmpty()){
            return PostResult.FALIURE_EMPTY_CONTENT
        }

        UserModelManager.getUserModel(v.getUserID()).addPost(txt)
        return PostResult.SUCCESS
    }
}