package com.grooveip.api.sdk.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by Patrick on 1/22/2018.
 */
class Sip(var userName: String,
          var password: String,
          var realm: String,
          var id: String,
          var endpointId: String
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(userName)
        writeString(password)
        writeString(realm)
        writeString(id)
        writeString(endpointId)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Sip> = object : Parcelable.Creator<Sip> {
            override fun createFromParcel(source: Parcel): Sip = Sip(source)
            override fun newArray(size: Int): Array<Sip?> = arrayOfNulls(size)
        }
    }
}