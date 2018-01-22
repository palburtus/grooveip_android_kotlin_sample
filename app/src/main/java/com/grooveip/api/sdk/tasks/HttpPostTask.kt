package com.grooveip.api.sdk.tasks

import android.os.AsyncTask
import com.grooveip.api.sampleapp.callbacks.ICallbackEvent
import com.grooveip.api.sdk.extensions.readTextAndClose
import com.grooveip.api.sdk.model.ReserveNumberResponse
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

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
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")

        connection.connect()

        var outputStream = connection.outputStream
        outputStream.write(body.toByteArray(Charset.forName("UTF-8")))
        outputStream.close()

        val responseCode = connection.responseCode

        if(responseCode == 200) {
            connection.getInputStream().readTextAndClose()
        }else{
            ""
        }


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