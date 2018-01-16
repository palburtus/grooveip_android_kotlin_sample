package com.grooveip.api.sdk.tasks

import android.os.AsyncTask
import com.grooveip.api.sampleapp.callbacks.ICallbackEvent
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Patrick on 1/15/2018.
 */
class HttpPostTask(val callbackEvent: ICallbackEvent<String, Exception>) : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String): String = try {
        var param = params[0]
        val body = params[1]

        val url = URL(param)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connect()

        var dataOutputStream = DataOutputStream(connection.outputStream)
        dataOutputStream.writeBytes(body)
        dataOutputStream.flush()
        dataOutputStream.close()

        var sb = StringBuilder();

        val reader = connection.inputStream.bufferedReader()
        reader.useLines {
            it.map { line ->
                sb.append(line)
            }
        }

        sb.toString()

    }catch (ex:Exception){
        callbackEvent.onError(ex)
        ""
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if(!result.isNullOrEmpty()) {
            callbackEvent.onSuccess(result)
        }
    }
}