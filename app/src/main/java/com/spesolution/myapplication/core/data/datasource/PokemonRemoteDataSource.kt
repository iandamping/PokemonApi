package com.spesolution.myapplication.core.data.datasource

import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResultsResponse
import com.spesolution.myapplication.core.data.model.DataSourceResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRemoteDataSource {

    suspend fun getMainPokemon(): DataSourceResult<List<PokemonResultsResponse>>

    suspend fun getDetailPokemon(url: String): PokemonResponse
}