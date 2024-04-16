package com.example.testtask.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testtask.data.repository.pet.PetApiService
import com.example.testtask.model.PetPhoto
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetPagingSource @Inject constructor(
    private val petApiService: PetApiService
): PagingSource<Int, Response<PetPhoto>>() {

    override fun getRefreshKey(state: PagingState<Int, Response<PetPhoto>>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response<PetPhoto>> {
        val page = params.key ?: 0
        return try{
            val pet = petApiService.getPets(page)
            LoadResult.Page(
                data = listOf(pet),
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}