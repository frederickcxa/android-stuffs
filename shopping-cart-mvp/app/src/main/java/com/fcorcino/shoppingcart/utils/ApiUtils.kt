package com.fcorcino.shoppingcart.utils

import okhttp3.*

class ApiUtils {
    companion object {
        @JvmField
        val httpClient = OkHttpClient()

        @JvmField
        val mediaTypeJson = MediaType.parse("application/json; charset=utf-8")

        private const val baseUrl = "http://192.168.8.102:8080"
        private const val pathProducts = "products"
        private const val methodGet = "GET"

        fun getProducts(): Call {
            val url = buildUrl(baseUrl, pathProducts)

            return doAsyncRequest(url, methodGet)
        }

        private fun buildUrl(vararg paths: String) = paths.joinToString(separator = "/")

        private fun doAsyncRequest(url: String, method: String, body: String = ""): Call {
            val requestBuilder = Request.Builder().url(url)

            if (method != (methodGet)) {
                requestBuilder.method(method, RequestBody.create(mediaTypeJson, body))
            }

            return httpClient.newCall(requestBuilder.build())
        }
    }
}
