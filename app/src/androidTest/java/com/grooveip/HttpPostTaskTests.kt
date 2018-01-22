package com.grooveip

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.grooveip.api.sampleapp.callbacks.ICallbackEvent
import com.grooveip.api.sdk.api.ApiClient
import com.grooveip.api.sdk.extensions.toJsonString
import com.grooveip.api.sdk.tasks.HttpPostTask

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HttpPostTaskTests {
    @Test
    fun executeTask_makesHttpPostRequest_returnsStringViaCallback() {

        val signal = CountDownLatch(1)
        val context = InstrumentationRegistry.getTargetContext()

        val number = "+18452089151"
        val areaCode = "845"

        val task = HttpPostTask(object : ICallbackEvent<String, Exception>{
            override fun onSuccess(obj: String) {
                assertTrue(!obj.isNullOrEmpty())
                signal.countDown()
            }

            override fun onError(obj: Exception) {
                fail(obj.message)
                signal.countDown()
            }
        })

        val apiRequest = ApiClient.buildReserveNumberRequest(number, areaCode);

        task.execute(apiRequest.url, apiRequest.body.toJsonString())

        signal.await()

    }
}
