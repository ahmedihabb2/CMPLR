package com.cmp.cmplr.Controller

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginControllerTest {

    var loginController = LoginController()

    @Test
    fun testLogin() {
        var result1: Int = loginController.getUserData("hgjfghf", "")



        assertThat(result1.toString()).isEqualTo("1")


    }

    @Test
    fun testLogin2() {
        var result2: Int = loginController.getUserData("dasfdasf@hotmail.com", "")
        assertThat(result2.toString()).isEqualTo("2")


    }

    @Test
    fun testLogin3() {
        var result3: Int = loginController.getUserData("a@hotmail.com", "58675")
        assertThat(result3.toString()).isEqualTo("0")

    }

    @Test
    fun testLogin4() {
        var result4: Int = loginController.getUserData("a@hotmail.com", "1234")
        assertThat(result4.toString()).isEqualTo("3")


    }
}