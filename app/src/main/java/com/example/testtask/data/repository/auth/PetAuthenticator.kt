package com.example.testtask.data.repository.auth

import com.example.testtask.data.preferences.TimeToken
import com.example.testtask.data.repository.auth.PetAuthService
import com.example.testtask.model.AuthInfo
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetAuthenticator @Inject constructor(
    private val petAuthService: PetAuthService,
    private val timeToken: TimeToken
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = petAuthService
            .getAccessToken(
                // TODO Find the id and key
                AuthInfo(
                    clientId = "",
                    clientSecret = ""
                )
            ).execute()
        return if(token.code() == 200){
            token.body()?.let { timeToken.saveToken(it) }
            response.request.newBuilder()
                .header("Authorization", "Bearer ${token.body()?.accessToken}")
                .build()
        } else null
    }
}