package com.grooveip.api.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Patrick on 1/22/2018.
 */
class ReserveNumberResponse(var userName: String,
                            var password: String,
                            var phoneNumber: String,
                            var userId: Int, var userToken: String, var creationDate: String, var sip: Sip) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readParcelable<Sip>(Sip::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(userName)
        writeString(password)
        writeString(phoneNumber)
        writeInt(userId)
        writeString(userToken)
        writeString(creationDate)
        writeParcelable(sip, 0)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ReserveNumberResponse> = object : Parcelable.Creator<ReserveNumberResponse> {
            override fun createFromParcel(source: Parcel): ReserveNumberResponse = ReserveNumberResponse(source)
            override fun newArray(size: Int): Array<ReserveNumberResponse?> = arrayOfNulls(size)
        }
    }
}