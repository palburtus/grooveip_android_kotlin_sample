package com.grooveip

import com.grooveip.api.sampleapp.extensions.formatPhoneNumber
import com.grooveip.api.sdk.extensions.hashSHA256
import com.grooveip.api.sdk.extensions.toJsonString
import com.grooveip.api.sdk.model.ReserveNumberRequest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExtensionsTests {

    private val apiSecret = "myapiscret"

    @Test
    fun formatPhoneNumber_removesPlusSignAndCountryCodeAndAddsDashes_returnsString(){

        val unformattedNumber = "+17329881021"

        assertEquals("732-988-1021", unformattedNumber.formatPhoneNumber())
    }

    @Test
    fun createSHA256_searchNumberApiParams_returnsString() {

        val clientId = 1;
        val areaCode = "732"
        val requestId = "c4h5dadb-8241-4765-8fed-845d35bbfe54/hash/293b945730fd13064bcee146989eec92272788a555369947b609a29d5bfd7avv";

        val request = "$clientId$areaCode$requestId$apiSecret"

        assertEquals("5b7252c27e483d6c92b1565f7386c3d1ec0dcf9f0d444a8760520a673c839528", request.hashSHA256())
    }


    @Test
    fun createSHA256_selectNumbersApiParams_returnsString() {

        val clientId = 1;
        val requestId = "d1f5dadb-b741-4765-8fed-742b35bbfe54/hash/293b945730fd13064bcee146989eec92272788a555369947b609a29d5bfd8b3a";
        val phoneNumber = "+17324005446"

        val request = "$clientId$phoneNumber$requestId$apiSecret"

        assertEquals("a78a56442bdf2178d0fd4a194a9ee8eca34bbef54c97a2de9da187a721e24990", request.hashSHA256())
    }

    @Test
    fun toJsonString_serializesReserveNumberRequestsToJsonStrings_returnsString() {

        val customerId = 1
        val number = "+18452089151"
        val areaCode = "845"
        val requestId = "c4h5dadb-8241-4765-8fed-845d35bbfe54/hash/293b945730fd13064bcee146989eec92272788a555369947b609a29d5bfd7avv";

        val hash = "$customerId$number$areaCode$requestId".hashSHA256()

        val request = ReserveNumberRequest(customerId, number, areaCode, requestId, hash)

        val json = request.toJsonString();

        assertEquals(
                "{\"CustomerId\":\"$customerId\", \"PhoneNumber\":\"$number\", \"AreaCode\":\"$areaCode\", \"Hash\":\"$hash\",\"RequestId\":\"$requestId\"}",
                json)

    }
}
