package com.cmp.cmplr.Mockup

class DatabaseMock {



    private val pairList = ArrayList<Pair<String, String>>()
    init {

        pairList.add(Pair("a@hotmail.com","1234"))
        pairList.add(Pair("b@hotmail.com","0019"))
        pairList.add(Pair("c@hotmail.com","6619"))
        pairList.add(Pair("d@hotmail.com","0000"))
    }

    fun isUser(email: String,password:String):Boolean {

        for (i in pairList.indices){

            if(email== pairList[i].first && password== pairList[i].second) {
                return true
            }

        }
        return false
    }
    fun insertUser(name:String, email:String, password:String):Boolean{
        return true

    }
    fun emailExist(email:String):Boolean{

        for (i in pairList.indices){

            if(email== pairList[i].first) {
                return true
            }

        }

        return false;
    }


}

