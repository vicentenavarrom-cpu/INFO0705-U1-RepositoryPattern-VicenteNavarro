package com.example.vetcare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.PetRepository

class PetViewModelFactory(
    private val repository: PetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            return PetViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}