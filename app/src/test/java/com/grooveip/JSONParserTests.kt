package com.grooveip

import com.grooveip.api.sdk.parsers.JsonParser
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn

/**
 * Created by Patrick on 1/22/2018.
 */
class JSONParserTests {

    @Test
    fun parseReserveNumberResponse_convertsJsonStringToObject_returnsReseveNumberResponse(){

        var jsonObject = Mockito.mock(JSONObject::class.java)
        var sipJsonObject = Mockito.mock(JSONObject::class.java)

        doReturn("2682885").`when`(sipJsonObject).optString("UserName", "")
        doReturn("fAL2D4ITgH").`when`(sipJsonObject).optString("SipPassword", "")
        doReturn("prd-evolution.bwapp.bwsip.io").`when`(sipJsonObject).optString("Realm", "")
        doReturn("gvip-2682885-4706").`when`(sipJsonObject).optString("SipId", "")
        doReturn("re-ye4wyaqw4vccjicuqc45ida").`when`(sipJsonObject).optString("EndpointId", "")

        doReturn(sipJsonObject).`when`(jsonObject).getJSONObject("Sip")

        doReturn("18452086619").`when`(jsonObject).optString("UserName", "")
        doReturn("YmtYSqr8").`when`(jsonObject).optString("Password", "")
        doReturn("+18452086619").`when`(jsonObject).optString("PhoneNumber", "")
        doReturn(2682885).`when`(jsonObject).optInt("UserId", -1)
        doReturn("550aa39b-c2a9-4491-9a5c-8755f764172d").`when`(jsonObject).optString("UserToken", "")
        doReturn("10-11-2018").`when`(jsonObject).optString("CreationDate", "")



        var parser = JsonParser()
        var response = parser.parseReserveNumberResponse(jsonObject)

        assertEquals("18452086619", response.userName)
        assertEquals("YmtYSqr8", response.password)
        assertEquals("+18452086619", response.phoneNumber)
        assertEquals(2682885, response.userId)
        assertEquals("550aa39b-c2a9-4491-9a5c-8755f764172d", response.userToken)
        assertEquals("10-11-2018", response.creationDate)

        assertEquals("2682885", response.sip.userName)
        assertEquals("fAL2D4ITgH", response.sip.password)
        assertEquals("gvip-2682885-4706", response.sip.id)
        assertEquals("prd-evolution.bwapp.bwsip.io", response.sip.realm)
        assertEquals("re-ye4wyaqw4vccjicuqc45ida", response.sip.endpointId)
    }
}