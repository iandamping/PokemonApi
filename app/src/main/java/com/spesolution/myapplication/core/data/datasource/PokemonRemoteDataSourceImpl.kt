package com.spesolution.myapplication.core.data.datasource

import com.spesolution.myapplication.core.data.datasource.remote.ApiInterface
import com.spesolution.myapplication.core.data.datasource.remote.BaseSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResultsResponse
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.data.model.Results
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRemoteDataSourceImpl @Inject constructor(
    baseSource: BaseSource,
    private val api: ApiInterface
) : PokemonRemoteDataSource, BaseSource by baseSource {
    override suspend fun getMainPokemon(): DataSourceResult<List<PokemonResultsResponse>> {
        return when (val response = oneShotCalls { api.getMainPokemon() }) {
            is Results.Error -> DataSourceResult.SourceError(response.exception)
            is Results.Success -> if (response.data.pokemonResults.isNullOrEmpty()) {
                DataSourceResult.SourceError(Exception(EMPTY_DATA))
            } else DataSourceResult.SourceValue(response.data.pokemonResults)
        }
    }

    override suspend fun getDetailPokemon(url: String): PokemonResponse {
        return api.getPokemon(url)
    }
}