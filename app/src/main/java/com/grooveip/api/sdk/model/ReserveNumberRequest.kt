package com.grooveip.api.sdk.model

/**
 * Created by Patrick on 1/20/2018.
 */
class ReserveNumberRequest(var customerId : Int,
                           var number : String,
                           var areaCode : String,
                           var requestId : String,
                           var hash : String) {
}