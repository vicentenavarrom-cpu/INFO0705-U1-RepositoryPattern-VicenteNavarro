package com.example.vetcare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Pet
import com.example.domain.repository.PetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetViewModel(
    private val repository: PetRepository
) : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())

    val pets: StateFlow<List<Pet>> = _pets

    fun loadPets() {
        viewModelScope.launch {
            _pets.value = repository.getPets()
        }
    }
}