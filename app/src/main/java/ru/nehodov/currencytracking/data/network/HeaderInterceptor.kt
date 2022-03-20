package ru.nehodov.currencytracking.data.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.nehodov.currencytracking.data.providers.BuildInfoProvider

class HeaderInterceptor(private val buildInfoProvider: BuildInfoProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = buildInfoProvider.apiKey
        val url = original.url.newBuilder().addQueryParameter(ApiConstants.KEY_QUERY, token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}