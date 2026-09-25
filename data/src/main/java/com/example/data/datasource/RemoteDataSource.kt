package com.example.data.datasource

import com.example.domain.model.Pet
import kotlinx.coroutines.delay

class RemoteDataSource {

    suspend fun getPets(): List<Pet> {
        delay(1000)
        return listOf(
            Pet(3, "Luna", "Perro", "Poodle", 2),
            Pet(4, "Simba", "Gato", "Persa", 4)
        )
    }
}
