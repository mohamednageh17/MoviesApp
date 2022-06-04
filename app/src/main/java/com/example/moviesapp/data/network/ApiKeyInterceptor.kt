package com.example.moviesapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor:Interceptor {
    private val apiKey="d6464c49a9503b302888a4a139f1ed70"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}