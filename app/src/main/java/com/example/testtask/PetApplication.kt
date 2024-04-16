package com.example.testtask

import android.app.Application
import android.util.Log
import com.example.testtask.data.repository.pet.PetApiService
import com.example.testtask.di.ApiModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetApplication: Application()