package com.spesolution.myapplication.module

import com.spesolution.myapplication.core.data.repository.PokemonRepositoryImpl
import com.spesolution.myapplication.core.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(ViewModelComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindsRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository
}