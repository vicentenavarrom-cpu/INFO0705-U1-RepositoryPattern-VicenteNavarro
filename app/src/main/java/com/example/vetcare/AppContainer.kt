package com.example.vetcare

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.data.repository.PetRepositoryImpl
import com.example.domain.repository.PetRepository

class AppContainer {

    private val localDataSource = LocalDataSource()

    private val remoteDataSource = RemoteDataSource()

    val petRepository: PetRepository = PetRepositoryImpl(
        localDataSource,
        remoteDataSource
    )
}