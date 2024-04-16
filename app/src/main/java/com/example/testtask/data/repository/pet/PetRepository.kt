package com.example.testtask.data.repository.pet

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testtask.model.PetPhoto
import com.example.testtask.paging.PetPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepository @Inject constructor(
    private val petApiService: PetApiService
){
    fun getPets(): Flow<PagingData<Response<PetPhoto>>> = Pager(
        config = PagingConfig(pageSize = 1000),
        pagingSourceFactory = { PetPagingSource(petApiService)}
    ).flow
}