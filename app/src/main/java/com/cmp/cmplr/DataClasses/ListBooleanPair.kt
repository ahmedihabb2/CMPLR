package com.cmp.cmplr.DataClasses

class ListBooleanPair(theList: ArrayList<HomePostData>, isSuccess: Boolean) {

    var innerList = theList
    var innerisSuccess = isSuccess
    fun changeTheData(newList: ArrayList<HomePostData>, isSuccessnew: Boolean) {
        innerList = newList
        innerisSuccess = isSuccessnew
    }

    fun getIsSucess(): Boolean {
        return innerisSuccess
    }

    fun getList(): ArrayList<HomePostData> {
        return innerList
    }
}