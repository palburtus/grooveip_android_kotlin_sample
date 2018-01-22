package com.grooveip.api.sdk.model

/**
 * Created by Patrick on 1/22/2018.
 */
class ReserveNumberResponse() {
    lateinit var userName:String
    lateinit var password:String
    lateinit var phoneNumber:String
    var userId:Int? = null
    lateinit var userToken:String
    lateinit var sip:Sip
}