package com.example.data.datasource

import com.example.domain.model.Pet

class LocalDataSource {

    private val pets = listOf(
        Pet(1, "Firulais", "Perro", "Labrador", 5),
        Pet(2, "Michi", "Gato", "Siamés", 3)
    )

    fun getPets(): List<Pet> = pets
}
