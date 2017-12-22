package com.grooveip

import com.grooveip.sdk.extensions.hashSHA256
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HashExtensionsTests {

    @Test
    fun createSHA256_selectNumbersApiParams_returnsString() {

        val apiSecret = "myapiscret";
        val clientId = 1;
        val requestId = "eda96875-af1d-4855-b529-57e86ea7cf75";
        val phoneNumber = "+17324005446"

        val request = "$clientId$phoneNumber$requestId$apiSecret"

        assertEquals("24fa66f850a39d3e381bf9d555d9678389ff71e177f19492525faf3cd9fe46b7", request.hashSHA256())
    }
}
