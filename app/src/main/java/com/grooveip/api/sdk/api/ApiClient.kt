package com.grooveip.api.sdk.api

import com.grooveip.api.sdk.extensions.hashSHA256
import com.grooveip.api.sdk.model.ApiRequest
import java.util.*

/**
 * Created by palburtus on 12/22/17.
 */
object ApiClient {

    val secret = "myapiscret"
    val clientId = 1;
    val baseUrl = "http://dev-commercial-api.azurewebsites.net/api"

    fun buildSearchNumbersUrl(areaCode:String) : String{

        val requestId = getRequestId()
        val hashParams = "$clientId$areaCode$requestId$secret"

        return "$baseUrl/numbers/list/$clientId" +
                "/areaCode/$areaCode/requestId/$requestId/hash/${hashParams.hashSHA256()}"

    }

    fun buildSelectNumberRequest(phoneNumber:String, areaCode: String) : ApiRequest{
        val requestId = getRequestId();
        val hashParams = "$clientId$phoneNumber$areaCode$requestId$secret"
        val body =  arrayOf<String>(clientId.toString(), phoneNumber, areaCode, requestId);

        return ApiRequest("$baseUrl/numbers/reserve", body);
    }

    fun getRequestId() : String {
        return UUID.randomUUID().toString();
    }

}