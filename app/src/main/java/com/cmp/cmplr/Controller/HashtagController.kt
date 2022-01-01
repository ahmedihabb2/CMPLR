package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.Model.HashtagModel

/**
 * hashtag fragment controller
 */
class HashtagController {
    var hashtagModel: HashtagModel = HashtagModel()

    /**
     * function to return the posts data to the fragment
     * @param hashtag hashtag wanted to get its posts
     * @param token  user token
     *
     * @return the list of posts data
     */
    suspend fun GetPostsBackend(hashtag: String?, token: String?): ListBooleanPair {
        hashtagModel.putToken(token)
        return hashtagModel.listReturn(hashtag)
    }
}