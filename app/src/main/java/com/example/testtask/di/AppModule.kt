package com.example.testtask.di

import android.app.Application
import com.example.testtask.data.preferences.TimeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideTimeToken(application: Application) = TimeToken(application)
}