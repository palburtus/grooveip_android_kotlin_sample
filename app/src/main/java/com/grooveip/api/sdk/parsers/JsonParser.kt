package com.grooveip.api.sdk.parsers

import com.grooveip.api.sdk.extensions.toStringList
import com.grooveip.api.sdk.model.ReserveNumberResponse
import org.json.JSONArray

/**
 * Created by palburtus on 12/26/17.
 */
class JsonParser(var json:String) {
    
    fun parseArrayToJonStringList() : MutableList<String> {
        
        val jsonObject = JSONArray(json)

        return jsonObject.toStringList();
    }

    fun parseReserveNumberResponse() : ReserveNumberResponse {
        var response = ReserveNumberResponse()



        return response
    }
}