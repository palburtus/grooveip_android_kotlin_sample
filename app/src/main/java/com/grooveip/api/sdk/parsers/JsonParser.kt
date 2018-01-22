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
        var response = ReserveNumberResponse()
        response.userName = jsonRootObject.optString("UserName")
        response.password = jsonRootObject.optString("Password")
        response.phoneNumber = jsonRootObject.optString("PhoneNumber")
        response.userId = jsonRootObject.optInt("UserId")
        response.userToken = jsonRootObject.optString("UserToken")

        if(jsonRootObject.has("Sip")) {

            var jsonSipObject = jsonRootObject.getJSONObject("Sip")

            var sip = Sip()

            sip.userName = jsonSipObject.optString("UserName")
            sip.password = jsonSipObject.optString("SipPassword")
            sip.realm = jsonSipObject.optString("Realm")
            sip.id = jsonSipObject.optString("SipId")
            sip.endpointId = jsonSipObject.optString("EndpointId")

            response.sip = sip
        }

        return response
    }
}