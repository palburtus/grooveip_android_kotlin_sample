package com.grooveip

import com.grooveip.api.sdk.parsers.JsonParser
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Patrick on 1/22/2018.
 */
class JSONParserTests {

    @Test
    fun parseReserveNumberResponse_convertsJsonStringToObject_returnsReseveNumberResponse(){
        var parser = JsonParser("{\"UserName\":\"18452084619\",\"Password\":\"YmtYSqr8\",\"PhoneNumber\":\"+18452084619\",\"UserId\":2682885,\"UserToken\":\"550aa39b-c2a9-4491-9a5c-8755f764172d\",\"Sip\":{\"UserName\":\"2682885\",\"SipPassword\":\"fAL2D4ITgH\",\"Realm\":\"prd-evolution.bwapp.bwsip.io\",\"SipId\":\"gvip-2682885-4706\",\"EndpointId\":\"re-ye4wyaqw4vccjicuqc45ida\"},\"Usage\":null}")
        var response = parser.parseReserveNumberResponse()

        assertEquals("18452086619", response.userName)
        assertEquals("YmtYSqr8", response.password)
        assertEquals("+18452086619", response.phoneNumber)
        assertEquals(2682885, response.userId)
        assertEquals("550aa39b-c2a9-4491-9a5c-8755f764172d", response.userToken)

        assertEquals("2682885", response.sip.userName)
        assertEquals("fAL2D4ITgH", response.sip.password)
        assertEquals("gvip-2682885-4706", response.sip.id)
        assertEquals("prd-evolution.bwapp.bwsip.io", response.sip.realm)
        assertEquals("re-ye4wyaqw4vccjicuqc45ida", response.sip.endpointId)
    }
}