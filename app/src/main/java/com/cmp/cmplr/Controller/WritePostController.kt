package com.cmp.cmplr.Controller

import com.cmp.cmplr.Model.managers.UserModelManager

object WritePostController {
    enum class PostResult {
        SUCCESS, FALIURE_EMPTY_CONTENT, INVALID_VAL;

        companion object {
            fun fromInt(i: Int?): PostResult {
                return when (i) {
                    1 -> SUCCESS
                    2 -> FALIURE_EMPTY_CONTENT
                    else -> INVALID_VAL
                }
            }

            fun toInt(res: PostResult): Int {
                return when (res) {
                    SUCCESS -> 1
                    FALIURE_EMPTY_CONTENT -> 2
                    else -> 0
                }
            }
        }
    }

    interface WritePostView {
        fun getPostText(): String
        fun getUserID(): String
        fun getBlogName(): String
    }

    fun writePost(v: WritePostView): PostResult {
        val txt = v.getPostText()
        if (txt.isNullOrEmpty()) {
            return PostResult.FALIURE_EMPTY_CONTENT
        }

        UserModelManager.getUserModel(v.getUserID())
            .addPost(v.getBlogName(), txt)
        return PostResult.SUCCESS
    }
}