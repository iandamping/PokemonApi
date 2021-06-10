package com.spesolution.myapplication.core.data.datasource

import com.spesolution.myapplication.core.data.datasource.remote.ApiInterface
import com.spesolution.myapplication.core.data.datasource.remote.BaseSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonSpeciesDetailResponse
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.data.model.Results
import com.spesolution.myapplication.core.domain.model.mapToPaging
import com.spesolution.myapplication.core.domain.response.PokemonPaging
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
    override suspend fun getPokemon(offset: Int): DataSourceResult<List<PokemonPaging>> {
        return when (val response = oneShotCalls { api.getPaginationMainPokemon(offset) }) {
            is Results.Error -> DataSourceResult.SourceError(response.exception)
            is Results.Success -> {
                val data = response.data.pokemonResults.map { singleItem ->
                    val results = api.getPokemon(singleItem.pokemonUrl)
                    results.mapToPaging(singleItem.pokemonUrl)
                }
                if (data.isNullOrEmpty()) {
                    DataSourceResult.SourceError(Exception(EMPTY_DATA))
                } else DataSourceResult.SourceValue(data)
            }
        }
    }

    override suspend fun getDetailPokemon(url: String): PokemonDetailResponse {
        return api.getPokemon(url)
    }

    override suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse {
        return api.getPokemonSpecies(url)
    }
}

// override fun getPagingPokemon(): PagingSource<Int, PokemonPaging> {
//     return object : PagingSource<Int, PokemonPaging>() {
//         override fun getRefreshKey(state: PagingState<Int, PokemonPaging>): Int? {
//             return state.anchorPosition?.let { anchorPosition ->
//                 state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                     ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//
//             }
//         }
//
//         override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonPaging> {
//             val position = params.key ?: NetworkConstant.POKEMON_STARTING_OFFSET
//             return try {
//                 when (val response =
//                     oneShotCalls { api.getPaginationMainPokemon(position * NetworkConstant.POKEMON_OFFSET) }) {
//                     is Results.Success -> {
//                         val data = response.data.pokemonResults.map { singleItem ->
//                             val results = api.getPokemon(singleItem.pokemonUrl)
//                             results.mapToPaging(singleItem.pokemonUrl)
//                         }
//                         if (data.isNullOrEmpty()) {
//                             LoadResult.Error(Throwable(EMPTY_DATA))
//                         } else {
//                             LoadResult.Page(
//                                 data = data,
//                                 prevKey = if (position == NetworkConstant.POKEMON_STARTING_OFFSET) null else position - 1,
//                                 nextKey = if (response.data.pokemonResults.isEmpty()) null else position + 1
//                             )
//                         }
//                     }
//                     is Results.Error -> {
//                         LoadResult.Error(response.exception)
//                     }
//                 }
//             } catch (exception: IOException) {
//                 LoadResult.Error(exception)
//             } catch (exception: HttpException) {
//                 LoadResult.Error(exception)
//             }
//         }
//     }
// }