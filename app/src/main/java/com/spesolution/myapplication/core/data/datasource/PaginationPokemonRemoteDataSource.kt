package com.spesolution.myapplication.core.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spesolution.myapplication.core.data.datasource.remote.ApiInterface
import com.spesolution.myapplication.core.data.datasource.remote.BaseSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.POKEMON_OFFSET
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.POKEMON_STARTING_OFFSET
import com.spesolution.myapplication.core.data.model.Results
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.core.domain.model.mapToDomain
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PaginationPokemonRemoteDataSource @Inject constructor(
    baseSource: BaseSource,
    private val api: ApiInterface
) : BaseSource by baseSource, PagingSource<Int, Pokemon>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: POKEMON_STARTING_OFFSET
        return try {
            when (val response =
                oneShotCalls { api.getPaginationMainPokemon(position * POKEMON_OFFSET) }) {
                is Results.Success -> {
                    val data = response.data.pokemonResults.map { singleItem ->
                        val results = api.getPokemon(singleItem.pokemonUrl)
                        results.mapToDomain()
                    }
                    if (data.isNullOrEmpty()){
                        LoadResult.Error(Throwable(EMPTY_DATA))
                    }else {
                        LoadResult.Page(
                            data = data,
                            prevKey = if (position == POKEMON_STARTING_OFFSET) null else position - 1,
                            nextKey = if (response.data.pokemonResults.isEmpty()) null else position + 1
                        )
                    }

                }
                is Results.Error -> {
                    LoadResult.Error(response.exception)
                }
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}