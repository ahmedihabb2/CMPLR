//package com.cmp.cmplr.Controller
//
//import com.google.common.truth.Truth.assertThat
//
//import org.junit.Test
//
//class SignupControllerTest {
//    var signupController = SignupController()
//
//    @Test
//    fun getSignupData0() {
//
//        var result0: Int = signupController.getSignupData("", "", "")
//
//
//
//        assertThat(result0.toString()).isEqualTo("0")
//    }
//
//    @Test
//    fun getSignupData1() {
//        var result1: Int = signupController.getSignupData("mo", "sdf", "")
//        assertThat(result1.toString()).isEqualTo("1")
//
//    }
//
//
//    @Test
//    fun getSignupData2() {
//        var result2: Int = signupController.getSignupData("mo", "a@hotmail.com", "")
//        assertThat(result2.toString()).isEqualTo("2")
//
//    }
//
//    @Test
//    fun getSignupData3() {
//        var result3: Int = signupController.getSignupData("mo", "kak@hotmial.com", "")
//        assertThat(result3.toString()).isEqualTo("3")
//
//    }
//
//
//    @Test
//    fun getSignupData4() {
//        var result4: Int = signupController.getSignupData("mo", "kak@hotmail.com", "32458")
//        assertThat(result4.toString()).isEqualTo("4")
//
//    }
//}