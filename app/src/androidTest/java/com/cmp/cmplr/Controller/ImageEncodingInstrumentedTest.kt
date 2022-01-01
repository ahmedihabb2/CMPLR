package com.cmp.cmplr.Controller

import android.net.Uri
import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cmp.cmplr.View.Activities.WritePostActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageEncodingInstrumentedTest {
    private lateinit var write : ActivityScenario<WritePostActivity>

    @Before //every test
    fun initActivity(){
        write = ActivityScenario.launch(WritePostActivity::class.java)
    }

    @Test
    fun failGracefullyIfGivenEmptyURI(){
        write.onActivity {
            assertEquals(
                it.imgToBase64(Uri.parse("")),
                "")
        }

    }

    @After //every test
    fun finalizeActivity(){
        write.close()
    }
}