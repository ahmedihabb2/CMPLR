package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.LoginData
import com.google.common.truth.Truth
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test


class LoginControllerTest{

    val loginController = LoginController()
    @Test
    fun `check email format with false data`()
    {
        val result = loginController.isEmail("ahmedihab")
        Truth.assertThat(result).isFalse()
    }
    @Test
    fun `check email format with false data 2`()
    {
        val result = loginController.isEmail("")
        Truth.assertThat(result).isFalse()
    }
    @Test
    fun `check email format with false data 3`()
    {
        val result = loginController.isEmail("ahmedihab@ cmplr.com")
        Truth.assertThat(result).isFalse()
    }
    @Test
    fun `check email format with valid data`()
    {
        val result = loginController.isEmail("ahmedihab@ihab")
        Truth.assertThat(result).isTrue()
    }
    @Test
    fun `check login with empty password `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = loginController.validateSignin(LoginData("ahmedihab@cmplr" , ""))
            }.join()
            Truth.assertThat(result.toString()).contains("401")
        }
    }
    @Test
    fun `check login with invalid password length `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = loginController.validateSignin(LoginData("ahmedihab@cmplr" , "abc"))
            }.join()
            Truth.assertThat(result.toString()).contains("401")
        }
    }
    @Test
    fun `check login with empty email `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = loginController.validateSignin(LoginData("" , "abc"))
            }.join()
            Truth.assertThat(result.toString()).contains("401")
        }
    }
    @Test
    fun `check login with invalid email format `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = loginController.validateSignin(LoginData("ihab.com" , "abc"))
            }.join()
            Truth.assertThat(result.toString()).contains("401")
        }
    }


}