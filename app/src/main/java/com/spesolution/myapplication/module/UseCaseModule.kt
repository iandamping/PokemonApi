package com.spesolution.myapplication.module

import com.spesolution.myapplication.core.domain.usecase.PokemonUseCase
import com.spesolution.myapplication.core.domain.usecase.PokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun bindsUseCase(usecase: PokemonUseCaseImpl): PokemonUseCase
}