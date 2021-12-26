package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.Model.HomeModel

class HomeController {
    var homeModel: HomeModel = HomeModel()
    suspend fun GetPostsBackend(token:String?): ListBooleanPair {
        homeModel.putToken(token)
        return homeModel.listReturn()
    }
}