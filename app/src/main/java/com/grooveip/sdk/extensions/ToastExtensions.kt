package com.grooveip.sdk.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by palburtus on 12/26/17.
 */

fun Context.toastShort(message: CharSequence){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}