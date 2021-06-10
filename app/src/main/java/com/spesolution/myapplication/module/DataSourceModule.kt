package com.spesolution.myapplication.module

import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSourceImpl
import com.spesolution.myapplication.core.data.datasource.cache.PokemonCacheDataSource
import com.spesolution.myapplication.core.data.datasource.cache.PokemonCacheDataSourceImpl
import com.spesolution.myapplication.core.data.datasource.remote.BaseSource
import com.spesolution.myapplication.core.data.datasource.remote.BaseSourceImpl
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
@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindsBaseSource(source: BaseSourceImpl): BaseSource

    @Binds
    @Singleton
    fun bindsCacheDataSource(source:PokemonCacheDataSourceImpl):PokemonCacheDataSource

    @Binds
    @Singleton
    fun bindsRemoteDataSource(source: PokemonRemoteDataSourceImpl): PokemonRemoteDataSource
}