package com.grooveip.sampleapp.callbacks

/**
 * Created by palburtus on 12/26/17.
 */

interface ICallbackEvent<T,E> {

    fun onSuccess(obj: T)
    fun onError(obj: E)
}