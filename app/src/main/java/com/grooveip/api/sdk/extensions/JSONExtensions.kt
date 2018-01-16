package com.grooveip.api.sdk.extensions

import org.json.JSONArray

/**
 * Created by palburtus on 12/26/17.
 */
fun JSONArray.toStringList() : MutableList<String>{

    var values = mutableListOf<String>()

    (0 until this!!.length()).mapTo(values) { this[it].toString() }

    return values
}