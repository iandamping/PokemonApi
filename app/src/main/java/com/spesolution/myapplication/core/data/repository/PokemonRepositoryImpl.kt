package com.spesolution.myapplication.core.data.repository

import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.domain.PokemonRepository
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRepositoryImpl @Inject constructor(private val remoteDataSource: PokemonRemoteDataSource) :
    PokemonRepository {
    override fun getPokemon(): Flow<DomainResult<List<Pokemon>>> {
        return flow {
            val results = remoteDataSource.getMainPokemon().map {
                val results = remoteDataSource.getDetailPokemon(it.pokemonUrl)
                Pokemon(
                    pokemonWeight = results.pokemonWeight,
                    pokemonHeight = results.pokemonHeight,
                    pokemonName = results.pokemonName
                )
            }
            if (results.isNullOrEmpty()) {
                emit(DomainResult.Error("Empty"))
            } else emit(DomainResult.Data(results))

        }.onStart { emit(DomainResult.Loading) }
    }
}