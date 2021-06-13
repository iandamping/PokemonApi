package com.spesolution.myapplication.core.data.datasource.cache

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonRemoteKeysEntity
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonDatabase
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonFavoriteDao
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonPaginationDao
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonRemoteKeyDao
import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.domain.model.mapToFavoriteDatabase
import com.spesolution.myapplication.core.domain.model.mapToPagingDatabase
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonCacheDataSourceImpl @Inject constructor(
    private val db:PokemonDatabase,
    private val paginationDao: PokemonPaginationDao,
    private val favoriteDao: PokemonFavoriteDao,
    private val remoteKeysDao:PokemonRemoteKeyDao
) : PokemonCacheDataSource {
    override val pokemonDatabase: PokemonDatabase
        get() = db

    override suspend fun savePagination(data: List<PokemonPaging>) {
       paginationDao.insertPokemon(*data.map { it.mapToPagingDatabase() }.toTypedArray())
    }

    override suspend fun saveFavorite(data: PokemonDetail) {
        favoriteDao.insertPokemon(data.mapToFavoriteDatabase())
    }

    override suspend fun saveRemoteKeys(data: List<PokemonRemoteKeysEntity>) {
        remoteKeysDao.insertKey(*data.toTypedArray())
    }

    override fun getPagination(): PagingSource<Int, PokemonPaginationEntity> {
      return paginationDao.loadPokemon()
    }

    override fun getListFavorite(): Flow<List<PokemonFavoriteEntity>> {
        return favoriteDao.loadPokemon()
    }

    override suspend fun getRemoteKeys(data: Long): PokemonRemoteKeysEntity? {
       return remoteKeysDao.remoteKeysRepoId(data)
    }

    override suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {  repo ->
                repo.pokemonId?.let { id ->
                    remoteKeysDao.remoteKeysRepoId(id)
                }
            }
    }

    override suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                repo.pokemonId?.let { id ->
                    remoteKeysDao.remoteKeysRepoId(id)
                }
            }
    }

    override suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PokemonPaginationEntity>): PokemonRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokemonId?.let { repoId ->
                remoteKeysDao.remoteKeysRepoId(repoId)
            }
        }
    }

    override suspend fun clearPagination() {
        paginationDao.deleteAllPokemon()
    }

    override suspend fun clearFavorite(id: Int) {
       favoriteDao.deleteFavoritePokemonById(id)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeysDao.clearRemoteKeys()
    }
}