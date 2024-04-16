package com.example.testtask.data.repository.pet

import com.example.testtask.model.PetPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetApiService {
    @GET("animals")
    suspend fun getPets(@Query("page") page: Int): Response<PetPhoto>
}