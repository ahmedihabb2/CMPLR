package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.Model.HomeModel

/**
 * class to be the controller of home fragment
 */
class HomeController {
    var homeModel: HomeModel = HomeModel()

    /**
     * function to return the posts data to the fragment
     * @param token  user token
     *
     * @return the list of posts data
     */
    suspend fun GetPostsBackend(token: String?): ListBooleanPair {
        homeModel.putToken(token)
        return homeModel.listReturn()
    }
}