package com.spesolution.myapplication.core.data.datasource

import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonSpeciesDetailResponse
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.domain.response.PokemonPaging

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRemoteDataSource {

    suspend fun getPokemon(offset: Int): DataSourceResult<List<PokemonPaging>>

    suspend fun getDetailPokemon(url: String): PokemonDetailResponse

    suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse
}