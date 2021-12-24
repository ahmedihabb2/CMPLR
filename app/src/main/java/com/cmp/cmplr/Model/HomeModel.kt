package com.cmp.cmplr.Model

import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.HomePostData

class HomeModel (){

    private var localStorage = LocalStorage()
    var token:String?=""
    var postList:ArrayList<HomePostData> =ArrayList()
    fun putToken(tokenPassed:String?){
        token=tokenPassed
    }
    fun listReturn():ArrayList<HomePostData>
    {
        //get the data
        return postList
    }


}