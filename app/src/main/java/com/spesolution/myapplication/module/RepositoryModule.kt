package com.spesolution.myapplication.module

import com.spesolution.myapplication.core.data.repository.PokemonRepositoryImpl
import com.spesolution.myapplication.core.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository
}