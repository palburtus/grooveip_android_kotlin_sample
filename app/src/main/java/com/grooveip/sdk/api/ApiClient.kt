package com.grooveip.sdk.api

import com.grooveip.sdk.extensions.hashSHA256
import java.util.*

/**
 * Created by palburtus on 12/22/17.
 */
object ApiClient {

    val secret = "myapiscret"
    val clientId = 1;
    val baseUrl = "http://dev-commercial-api.azurewebsites.net/api/"

    fun buildSearchNumbersRequest(areaCode:String) : String{

        val requestId = getRequestId()
        val requestParams = "$clientId$areaCode$requestId$secret"

        val url = "${baseUrl}numbers/list/$clientId" +
                "/areaCode/$areaCode/requestId/$requestId/hash/${requestParams.hashSHA256()}"

        return url
    }

    fun getRequestId() : String {
        return UUID.randomUUID().toString();
    }

}