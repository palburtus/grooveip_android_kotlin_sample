package com.grooveip.api.sampleapp.extensions

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import com.grooveip.api.R

/**
 * Created by Patrick on 1/22/2018.
 */
fun Context.showIndefinentSnackbarWithRetry(message : String, view : View, callback:View.OnClickListener) : Snackbar {
    return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry, callback)
}