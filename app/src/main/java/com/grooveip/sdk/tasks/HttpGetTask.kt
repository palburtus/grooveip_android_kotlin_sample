package com.grooveip.sdk.tasks

import android.os.AsyncTask
import com.grooveip.sdk.callbacks.ICallbackEvent
import java.net.URL

/**
 * Created by palburtus on 12/26/17.
 */
class HttpGetTask(val callbackEvent:ICallbackEvent<String, Exception>) : AsyncTask<String, Void, String?>() {

    override fun doInBackground(vararg params: String): String? = try {
        var param = params[0];

        val result = URL(param).readText()

        result;
    }catch (ex:Exception){
        callbackEvent.onError(ex)
        null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(result != null) {
            callbackEvent.onSuccess(result)
        }
    }
}