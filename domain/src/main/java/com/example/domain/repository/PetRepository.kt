package com.example.domain.repository

import com.example.domain.model.Pet

interface PetRepository {
    suspend fun getPets(): List<Pet>
}
