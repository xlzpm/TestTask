package com.example.testtask.di

import com.example.testtask.data.repository.auth.PetAuthInterceptor
import com.example.testtask.data.repository.auth.PetAuthService
import com.example.testtask.data.repository.auth.PetAuthenticator
import com.example.testtask.data.repository.pet.PetApiService
import com.example.testtask.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun providePetApiService(
        authInterceptor: PetAuthInterceptor,
        authenticator: PetAuthenticator
    ): PetApiService =
        retrofit
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .authenticator(authenticator)
                    .build()
                )
            .build()
            .create(PetApiService::class.java)

    @Provides
    @Singleton
    fun providePetAuthService(
        authInterceptor: PetAuthInterceptor
    ): PetAuthService =
        retrofit
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build()
            )
            .build()
            .create(PetAuthService::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
}