package br.com.smartgames.http

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class HttpHelper {
    private val BASE_URL = "http://192.168.42.36:3001"

    val client = OkHttpClient()

    fun get(endpoint: String, callback: Callback) {
        val request = Request.Builder()
            .url(BASE_URL + endpoint)
            .get().build()

        client.newCall(request).enqueue(callback)
    }
}