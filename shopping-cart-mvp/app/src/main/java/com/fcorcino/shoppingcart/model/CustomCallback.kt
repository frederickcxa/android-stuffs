package com.fcorcino.shoppingcart.model

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

abstract class CustomCallback : Callback {
    override fun onFailure(call: Call, ex: IOException) {
        onFailure(ex)
    }

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            onSuccess(JSONObject(response.body()?.string()))
        } else {
            onFailure(IOException("The call could not be made."))
        }

        onFinish()
    }

    abstract fun onFailure(ex: IOException)

    abstract fun onSuccess(response: JSONObject)

    abstract fun onFinish()
}
