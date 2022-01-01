package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.FollowingData
import com.cmp.cmplr.DataClasses.RecommendedData
import com.cmp.cmplr.Model.FollowingModel
import com.google.gson.Gson

/**
 * class to control the following fragment
 *
 */
class FollowingController {
    val followingModel = FollowingModel()

    /**
     *  function to return back the following data
     *
     *  @param token user token
     *  @return listofFollowing
     */
    suspend fun fetchFollowingCont(token : String) : ArrayList<FollowingData>
    {
        val  gson = Gson()
        val listOfFollowing : ArrayList<FollowingData> = ArrayList()
        val jsonObj = followingModel.fetchFollowingMod(token)
        if(jsonObj != null)
        {
            val blogList = jsonObj.getAsJsonArray("blogs")
            for(blog in blogList)
            {
                listOfFollowing.add(
                    gson.fromJson(
                        blog.asJsonObject,
                        FollowingData::class.java
                    )
                )
            }
        }
        return listOfFollowing
    }
}