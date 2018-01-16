package com.grooveip.api.sdk.extensions

import java.security.MessageDigest

/**
 * Created by palburtus on 12/22/17.
 */

fun String.hashSHA256(): String {
    val bytes = this.toByteArray()
    val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}