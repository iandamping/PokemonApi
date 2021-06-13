package com.spesolution.myapplication.core.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRepository {

    @ExperimentalPagingApi
    fun getPagingPokemon(): RemoteMediator<Int, PokemonPaginationEntity>

    fun getDetailPokemon(url:String): Flow<PokemonDetail>

    fun getDetailSpeciesPokemon(url:String): Flow<PokemonDetailSpecies>

    fun getCachePagination(): PagingSource<Int, PokemonPaginationEntity>

    suspend fun saveFavorite(data: PokemonDetail)

    suspend fun clearFavorite(id: Int)

    fun getListFavorite(): Flow<List<PokemonFavoriteEntity>>
}