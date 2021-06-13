package com.spesolution.myapplication.core.data.datasource.cache

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonRemoteKeysEntity
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonDatabase
import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonCacheDataSource {

    val pokemonDatabase:PokemonDatabase

    suspend fun savePagination(data: List<PokemonPaging>)

    suspend fun saveFavorite(data: PokemonDetail)

    suspend fun saveRemoteKeys(data: List<PokemonRemoteKeysEntity>)

    fun getPagination(): PagingSource<Int, PokemonPaginationEntity>

    fun getListFavorite(): Flow<List<PokemonFavoriteEntity>>

    suspend fun getRemoteKeys(data: Long):PokemonRemoteKeysEntity?

    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity?

    suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity?

    suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity?

    suspend fun clearPagination()

    suspend fun clearFavorite(id: Int)

    suspend fun clearRemoteKeys()
}