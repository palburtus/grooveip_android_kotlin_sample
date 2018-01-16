package com.grooveip.api.sdk.parsers

import com.grooveip.api.sdk.extensions.toStringList
import org.json.JSONArray

/**
 * Created by palburtus on 12/26/17.
 */
class JsonStringListParser(var json:String) {
    
    fun parser() : MutableList<String> {
        
        val jsonObject = JSONArray(json)

        return jsonObject.toStringList();
    }
}