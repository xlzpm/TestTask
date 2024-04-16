package com.example.testtask.data.repository.auth

import com.example.testtask.model.AuthInfo
import com.example.testtask.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PetAuthService {

    @POST("oauth2/token")
    fun getAccessToken(@Body authInfo: AuthInfo): Call<Token>
}