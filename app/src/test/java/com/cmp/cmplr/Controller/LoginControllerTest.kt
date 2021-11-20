package com.cmp.cmplr.Controller

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginControllerTest{

    var loginController=LoginController()
    @Test
    fun testLogin() {
        var result1:Int =loginController.getUserData("hgjfghf","")

        var result2:Int =loginController.getUserData("dasfdasf@hotmail.com","")
        var result3 :Int=loginController.getUserData("a@hotmail.com","58675")
        var result4 :Int=loginController.getUserData("a@hotmail.com","1234")


        assertThat(result1.toString()).isEqualTo("1")
        assertThat(result2.toString()).isEqualTo("2")
        assertThat(result3.toString()).isEqualTo("0")
        //assertThat(result4.toString()).isEqualTo("3")



    }
}