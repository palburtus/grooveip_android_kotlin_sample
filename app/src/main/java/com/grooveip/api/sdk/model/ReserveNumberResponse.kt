package com.grooveip.api.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Patrick on 1/22/2018.
 */
class ReserveNumberResponse() : Parcelable {
    lateinit var userName: String

    lateinit var password: String

    lateinit var phoneNumber: String

    var userId: Int? = null

    lateinit var userToken: String

    lateinit var sip: Sip

    //Parcelable Implementation

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ReserveNumberResponse> = object : Parcelable.Creator<ReserveNumberResponse> {
            override fun createFromParcel(source: Parcel): ReserveNumberResponse = ReserveNumberResponse(source)
            override fun newArray(size: Int): Array<ReserveNumberResponse?> = arrayOfNulls(size)
        }
    }
}