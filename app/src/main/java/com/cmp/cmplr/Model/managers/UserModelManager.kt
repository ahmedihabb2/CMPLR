package com.cmp.cmplr.Model.managers

import com.cmp.cmplr.Model.UserModel

object UserModelManager {
    private var userModels = HashMap<Int, UserModel>()

    fun getUserModel(ID: Int) : UserModel {
        if(!userModels.containsKey(ID)) {
            userModels[ID] = UserModel(ID)
        }

        return userModels[ID]!!
    }
}