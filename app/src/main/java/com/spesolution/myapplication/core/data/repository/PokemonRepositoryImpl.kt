package com.spesolution.myapplication.core.data.repository

import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.domain.PokemonRepository
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
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
            when (val main = remoteDataSource.getMainPokemon()) {
                is DataSourceResult.SourceValue -> {
                    val data = main.data.map { singleItem ->
                        val results = remoteDataSource.getDetailPokemon(singleItem.pokemonUrl)
                        Pokemon(
                            pokemonWeight = results.pokemonWeight,
                            pokemonHeight = results.pokemonHeight,
                            pokemonName = results.pokemonName,
                            pokemonImage = results.pokemonImage.sprites.other.image,
                            pokemonSmallImage1 = results.pokemonImage.smallImage1,
                            pokemonSmallImage2 = results.pokemonImage.smallImage2,
                            pokemonSmallImage3 = results.pokemonImage.smallImage3,
                            pokemonSmallImage4 = results.pokemonImage.smallImage4
                        )
                    }
                    if (data.isNullOrEmpty()) {
                        emit(DomainResult.Error(EMPTY_DATA))
                    } else emit(DomainResult.Data(data))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(main.exception.message ?: NETWORK_ERROR))
                }

            }

        }.onStart { emit(DomainResult.Loading) }
    }
}

