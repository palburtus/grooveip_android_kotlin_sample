package com.grooveip.sdk.parsers

import com.grooveip.sdk.extensions.toStringList
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by palburtus on 12/26/17.
 */
class JsonStringListParser(var json:String) {
    
    fun parser() : MutableList<String> {
        
        val jsonObject = JSONArray(json)

        return jsonObject.toStringList();
    }
}