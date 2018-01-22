package com.grooveip.api.sdk.extensions

import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by Patrick on 1/22/2018.
 */
fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}