package com.cmp.cmplr.API

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepositoryImp():RemoteRepository {


    //private var tokenUser: String?=""
    override suspend fun homepost(token_h: String?)=
        withContext(Dispatchers.IO){
            Api_Instance.api.homepost(token_h)
        }
//    fun PassToken(token: String?){
//        tokenUser=token
//    }

}