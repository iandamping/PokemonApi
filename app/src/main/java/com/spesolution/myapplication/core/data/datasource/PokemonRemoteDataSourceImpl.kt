package com.spesolution.myapplication.core.data.datasource

import com.spesolution.myapplication.core.data.datasource.remote.ApiInterface
import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResultsResponse
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface
) : PokemonRemoteDataSource {
    override suspend fun getMainPokemon(): List<PokemonResultsResponse> {
        return api.getMainPokemon().pokemonResults
    }

    override suspend fun getDetailPokemon(url: String): PokemonResponse {
        return api.getPokemon(url)
    }
}