package com.example.testtask.ui.main

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.cachedIn
import com.example.testtask.PetApplication
import com.example.testtask.data.repository.pet.PetRepository
import com.example.testtask.model.PetPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(
    private val petRepository: PetRepository
): ViewModel(){
    val scale = mutableFloatStateOf(1f)

    private val _selectedPet = MutableStateFlow<PetPhoto?>(null)
    val selectedPet: StateFlow<PetPhoto?>
        get() = _selectedPet

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _items = MutableStateFlow(listOf(selectedPet))
    val items = _items.asStateFlow()

    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        _items.value = listOf(selectedPet)
        _isRefreshing.update { false }
    }

    fun getPets() = petRepository.getPets().cachedIn(viewModelScope)
}