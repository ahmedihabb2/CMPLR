package com.cmp.cmplr.Model

import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.API.PostData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Mocking user posts using array
 *
 * @constructor
 *
 *
 * @param ID
 */
class UserModel(private val token: String) {

    fun addPost(blogName: String, postTxt: String) {
        val auth = "Bearer $token"
        CoroutineScope(Dispatchers.IO).launch {
            Api_Instance.api.writePost(auth, PostData(blogName, postTxt))
        }
    }

}