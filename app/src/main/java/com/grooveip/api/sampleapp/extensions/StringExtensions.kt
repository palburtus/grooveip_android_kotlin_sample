package com.grooveip.api.sampleapp.extensions

/**
 * Created by Patrick on 1/22/2018.
 */

fun String.formatPhoneNumber() : String{
    var formatted = this.replace("+1", "")
    return "${formatted.substring(0, 3)}-${formatted.substring(3, 6)}-${formatted.substring(6, 10)}"
}