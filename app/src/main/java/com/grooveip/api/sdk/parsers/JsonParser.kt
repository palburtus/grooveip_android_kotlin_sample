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

    fun parseReserveNumberResponse() : ReserveNumberResponse {

        var jsonRootObject = JSONObject(json)

        var response = ReserveNumberResponse()
        response.userName = jsonRootObject.getString("UserName")
        response.password = jsonRootObject.getString("Password")
        response.phoneNumber = jsonRootObject.getString("PhoneNumber")
        response.userId = jsonRootObject.getInt("UserId")
        response.userToken = jsonRootObject.getString("UserToken")

        var jsonSipObject = jsonRootObject.getJSONObject("Sip")

        var sip = Sip()

        sip.userName = jsonSipObject.getString("UserName")
        sip.password = jsonSipObject.getString("SipPassword")
        sip.realm = jsonSipObject.getString("Realm")
        sip.id = jsonSipObject.getString("SipId")
        sip.endpointId = jsonSipObject.getString("EndpointId")

        response.sip = sip

        return response
    }
}