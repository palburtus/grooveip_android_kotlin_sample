package com.grooveip.api.sdk.api

import com.grooveip.api.sdk.extensions.hashSHA256
import com.grooveip.api.sdk.model.ApiRequest
import com.grooveip.api.sdk.model.ReserveNumberRequest
import java.util.*

/**
 * Created by palburtus on 12/22/17.
 */
object ApiClient {

    val secret = "mysecretkey"
    val clientId = 1;
    val baseUrl = "http://dev-commercial-api.azurewebsites.net/api"

    fun buildNumbersInventoryUrl() : String{

        val page = "1"
        val quantity = "100"
        val requestId = getRequestId()
        val hash = "$clientId$page$quantity$requestId".hashSHA256()

        return "$baseUrl/numbers/inventory/$clientId/page/$page/quantity/$quantity/requestId/$requestId/hash/$hash"
    }

    fun buildSearchNumbersUrl(areaCode:String) : String{

        val requestId = getRequestId()
        val hashParams = "$clientId$areaCode$requestId$secret"

        return "$baseUrl/numbers/list/$clientId" +
                "/areaCode/$areaCode/requestId/$requestId/hash/${hashParams.hashSHA256()}"

    }

    fun buildReserveNumberRequest(phoneNumber:String, areaCode: String) : ApiRequest<ReserveNumberRequest>{
        val requestId = getRequestId();
        val hashParams = "$clientId$phoneNumber$areaCode$requestId$secret"
        val body =  arrayOf<String>(clientId.toString(), phoneNumber, areaCode, requestId);

        val request = ReserveNumberRequest(clientId, phoneNumber, areaCode, requestId, hashParams.hashSHA256())

        return ApiRequest("$baseUrl/numbers/reserve", request);
    }

    fun getRequestId() : String {
        return UUID.randomUUID().toString();
    }

}