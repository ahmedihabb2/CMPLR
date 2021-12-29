package com.cmp.cmplr.API

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp : RemoteRepository {


    //private var tokenUser: String?=""
    override suspend fun homepost(token_h: String?) =
        withContext(Dispatchers.IO) {
            Api_Instance.api.homepost(token_h)
        }
//    fun PassToken(token: String?){
//        tokenUser=token
//    }

}