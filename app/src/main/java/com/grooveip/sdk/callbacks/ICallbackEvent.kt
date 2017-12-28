package com.grooveip.sdk.callbacks

/**
 * Created by palburtus on 12/26/17.
 */

interface ICallbackEvent<T,E> {

    fun onSuccess(obj: T)
    fun onError(obj: E)
}