package com.spesolution.myapplication.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.cache.PokemonCacheDataSource
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonRemoteKeysEntity
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.POKEMON_STARTING_OFFSET
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.data.model.Results
import com.spesolution.myapplication.core.domain.repository.PokemonRepository
import com.spesolution.myapplication.core.domain.model.mapToDetail
import com.spesolution.myapplication.core.domain.model.mapToPaging
import com.spesolution.myapplication.core.domain.model.mapToSpeciesDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val cacheDataSource: PokemonCacheDataSource
) :
    PokemonRepository {
    @ExperimentalPagingApi
    override fun getPagingPokemon(): RemoteMediator<Int, PokemonPaginationEntity> {
        return object :RemoteMediator<Int, PokemonPaginationEntity>(){
            override suspend fun load(
                loadType: LoadType,
                state: PagingState<Int, PokemonPaginationEntity>
            ): MediatorResult {
                val page = when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = cacheDataSource.getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: POKEMON_STARTING_OFFSET
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = cacheDataSource.getRemoteKeyForFirstItem(state)
                        // If remoteKeys is null, that means the refresh result is not in the database yet.
                        // We can return Success with `endOfPaginationReached = false` because Paging
                        // will call this method again if RemoteKeys becomes non-null.
                        // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                        // the end of pagination for prepend.
                        val prevKey = remoteKeys?.prevKey
                        if (prevKey == null) {
                            return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                        }
                        prevKey
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = cacheDataSource.getRemoteKeyForLastItem(state)
                        // If remoteKeys is null, that means the refresh result is not in the database yet.
                        // We can return Success with `endOfPaginationReached = false` because Paging
                        // will call this method again if RemoteKeys becomes non-null.
                        // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                        // the end of pagination for append.
                        val nextKey = remoteKeys?.nextKey
                        if (nextKey == null) {
                            return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                        }
                        nextKey
                    }

                }

                try {
                    when(val apiResponse = remoteDataSource.getPokemon(page * NetworkConstant.POKEMON_OFFSET)){
                        is DataSourceResult.SourceError ->  return MediatorResult.Error(apiResponse.exception)
                        is DataSourceResult.SourceValue -> {
                            val data = apiResponse.data
                            val endOfPaginationReached = data.isEmpty()
                            cacheDataSource.pokemonDatabase.withTransaction {
                                // clear all tables in the database
                                if (loadType == LoadType.REFRESH) {
                                    with(cacheDataSource){
                                        clearRemoteKeys()
                                        clearPagination()
                                    }
                                }
                                val prevKey = if (page == POKEMON_STARTING_OFFSET) null else page - 1
                                val nextKey = if (endOfPaginationReached) null else page + 1
                                val keys = data.map {
                                    PokemonRemoteKeysEntity(pokeId = null,prevKey = prevKey, nextKey = nextKey)
                                }
                                with(cacheDataSource){
                                    saveRemoteKeys(keys)
                                    savePagination(data)
                                }
                            }
                            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                        }
                    }
                } catch (exception: IOException) {
                    return MediatorResult.Error(exception)
                } catch (exception: HttpException) {
                    return MediatorResult.Error(exception)
                }
            }
        }
    }

    override fun getDetailPokemon(url: String): Flow<PokemonDetail> {
        return flow {
            emit(remoteDataSource.getDetailPokemon(url).mapToDetail())
        }
    }

    override fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies> {
        return flow {
            emit(remoteDataSource.getDetailSpeciesPokemon(url).mapToSpeciesDetail())
        }
    }

    override fun getCachePagination(): PagingSource<Int, PokemonPaginationEntity> {
        return cacheDataSource.getPagination()
    }
}

