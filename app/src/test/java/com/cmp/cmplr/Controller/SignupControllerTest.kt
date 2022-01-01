package com.cmp.cmplr.Controller
import com.cmp.cmplr.DataClasses.SignupData
import com.google.common.truth.Truth.assertThat
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SignupControllerTest {
    val signupController  = SignupController()
    @Test
    fun `check email format with false data`()
    {
        val result = signupController.isEmail("ahmedihab")
        assertThat(result).isFalse()
    }
    @Test
    fun `check email format with false data 2`()
    {
        val result = signupController.isEmail("")
        assertThat(result).isFalse()
    }
    @Test
    fun `check email format with false data 3`()
    {
        val result = signupController.isEmail("ahmedihab@ cmplr.com")
        assertThat(result).isFalse()
    }
    @Test
    fun `check email format with valid data`()
    {
        val result = signupController.isEmail("ahmedihab@ihab")
        assertThat(result).isTrue()
    }
    @Test
   fun `check signup data with empty password `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = signupController.validateData(SignupData("ahmed", "", "Ihab", 20))
            }.join()
            assertThat(result.toString()).contains("422")
        }
    }
    @Test
    fun `check signup data with empty mail `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = signupController.validateData(SignupData("", "Testing", "Ihab", 20))
            }.join()
            assertThat(result.toString()).contains("422")
        }
    }
    @Test
    fun `check signup data with empty blogname `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = signupController.validateData(SignupData("ahmedihab@ihab.com", "Testing", "", 20))
            }.join()
            assertThat(result.toString()).contains("422")
        }
    }
    @Test
    fun `check signup data with empty invalid mail format `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = signupController.validateData(SignupData("ahmedihab.com", "Testing", "IhabBlog", 20))
            }.join()
            assertThat(result.toString()).contains("422")
        }
    }
    @Test
    fun `check signup data with invalid password length `()
    {
        var result = JsonObject()
        runBlocking {
            GlobalScope.launch {
                result = signupController.validateData(SignupData("ahmedihab@ihab.com", "Test", "IhabBlog", 20))
            }.join()
            assertThat(result.toString()).contains("422")
        }
    }

}