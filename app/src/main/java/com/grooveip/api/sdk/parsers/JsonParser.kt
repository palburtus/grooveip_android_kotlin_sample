package com.grooveip.api.sdk.parsers

import com.grooveip.api.sdk.extensions.toStringList
import com.grooveip.api.sdk.model.ReserveNumberResponse
import com.grooveip.api.sdk.model.Sip
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by palburtus on 12/26/17.
 */
class JsonParser {
    
    fun parseArrayToJonStringList(jsonArray: JSONArray) : MutableList<String> {

        return jsonArray.toStringList();
    }

    fun parseInventoryResponse(jsonArray: JSONArray) : List<ReserveNumberResponse>{

        return (0 until jsonArray.length()).map { convertJsonObjectToReserveNumberResponse(jsonArray.getJSONObject(it)) }
    }

    fun parseReserveNumberResponse(jsonRootObject: JSONObject) : ReserveNumberResponse {

        return convertJsonObjectToReserveNumberResponse(jsonRootObject)
    }

    private fun convertJsonObjectToReserveNumberResponse(jsonRootObject: JSONObject) : ReserveNumberResponse {

        val jsonSipObject = jsonRootObject.getJSONObject("Sip")

        val sip = Sip(jsonSipObject.optString("UserName", ""),
                jsonSipObject.optString("SipPassword", ""),
                jsonSipObject.optString("Realm", ""),
                jsonSipObject.optString("SipId", ""),
                jsonSipObject.optString("EndpointId", ""))

        return ReserveNumberResponse(
                jsonRootObject.optString("UserName", ""),
                jsonRootObject.optString("Password", ""),
                jsonRootObject.optString("PhoneNumber", ""),
                jsonRootObject.optInt("UserId", -1),
                jsonRootObject.optString("UserToken", ""),
                jsonRootObject.optString("CreationDate", ""),
                sip)
    }
}