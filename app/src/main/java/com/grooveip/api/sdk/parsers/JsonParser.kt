package com.grooveip.api.sdk.parsers

import com.grooveip.api.sdk.extensions.toStringList
import com.grooveip.api.sdk.model.ReserveNumberResponse
import com.grooveip.api.sdk.model.Sip
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by palburtus on 12/26/17.
 */
class JsonParser(var json:String) {
    
    fun parseArrayToJonStringList() : MutableList<String> {
        
        val jsonObject = JSONArray(json)

        return jsonObject.toStringList();
    }

    fun parseInventoryResponse() : List<ReserveNumberResponse>{
        val jsonArray = JSONArray(json)

        val responses = mutableListOf<ReserveNumberResponse>()

        for(i in 0..jsonArray.length() - 1){
            responses.add(convertJsonObjecTotReserveNumberResponse(jsonArray.getJSONObject(i)))
        }

        return responses
    }

    fun parseReserveNumberResponse() : ReserveNumberResponse {

        var jsonRootObject = JSONObject(json)

        return convertJsonObjecTotReserveNumberResponse(jsonRootObject)
    }

    fun convertJsonObjecTotReserveNumberResponse(jsonRootObject: JSONObject) : ReserveNumberResponse {

        var jsonSipObject = jsonRootObject.getJSONObject("Sip")

        var sip = Sip(jsonSipObject.optString("UserName", ""),
                jsonSipObject.optString("SipPassword", ""),
                jsonSipObject.optString("Realm", ""),
                jsonSipObject.optString("SipId", ""),
                jsonSipObject.optString("EndpointId", ""))

        var response = ReserveNumberResponse(
                jsonRootObject.optString("UserName", ""),
                jsonRootObject.optString("Password", ""),
                jsonRootObject.optString("PhoneNumber", ""),
                jsonRootObject.optInt("UserId", -1),
                jsonRootObject.optString("UserToken", ""),
                jsonRootObject.optString("CreationDate", ""),
                sip)

        return response
    }
}