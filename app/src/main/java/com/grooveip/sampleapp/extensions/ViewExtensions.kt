package com.grooveip.sampleapp.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by palburtus on 12/21/17.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.setVisibilityGoneIfVisible(){
    if(this.visibility == View.VISIBLE){
        this.visibility = View.GONE
    }
}

fun View.setVisibilityVisibleIfNotVisible(){
    if(this.visibility != View.VISIBLE){
        this.visibility = View.VISIBLE
    }
}
