package com.cmp.cmplr.Model.managers

import com.cmp.cmplr.Model.UserModel

/**
 * Singleton class acts as a single of access for user models
 */
object UserModelManager {
    private var userModels = HashMap<Int, UserModel>()

    fun getUserModel(ID: Int) : UserModel {
        if(!userModels.containsKey(ID)) {
            userModels[ID] = UserModel(ID)
        }

        return userModels[ID]!!
    }
}