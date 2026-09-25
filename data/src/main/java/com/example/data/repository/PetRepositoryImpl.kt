package com.example.data.repository

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.domain.model.Pet
import com.example.domain.repository.PetRepository

class PetRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : PetRepository {

    override suspend fun getPets(): List<Pet> {
        val localPets = localDataSource.getPets()
        val remotePets = remoteDataSource.getPets()
        return localPets + remotePets
    }
}
