package com.example.testtask.data.repository.auth

import com.example.testtask.data.preferences.TimeToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetAuthInterceptor @Inject constructor(
    private val timeToken: TimeToken
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            timeToken.getToken()?.let { header("Authorization", "Bearer $it") }
        }.build()

        return chain.proceed(request)
    }
}