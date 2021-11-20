package com.cmp.cmplr.Controller
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class SignupControllerTest {
    var signupController=SignupController()
    @Test
    fun getSignupData() {

        var result0:Int=signupController.getSignupData("","","")
        var result1:Int=signupController.getSignupData("mo","sdf","")

        var result2:Int=signupController.getSignupData("mo","a@hotmail.com","")

        var result3:Int=signupController.getSignupData("mo","kak@hotmial.com","")

        var result4:Int=signupController.getSignupData("mo","kak@hotmail.com","32458")
        assertThat(result0.toString()).isEqualTo("0")
        assertThat(result1.toString()).isEqualTo("1")
        assertThat(result2.toString()).isEqualTo("2")
        assertThat(result3.toString()).isEqualTo("3")
        assertThat(result4.toString()).isEqualTo("4")
    }
}