package com.spesolution.myapplication.core.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.NETWORK_PAGE_SIZE
import com.spesolution.myapplication.core.domain.model.mapToDomainPaging
import com.spesolution.myapplication.core.domain.repository.PokemonRepository
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonUseCaseImpl @Inject constructor(private val repository: PokemonRepository) :
    PokemonUseCase {
    @ExperimentalPagingApi
    override fun getPagingPokemon(): Flow<PagingData<PokemonPaging>> {
        val pagingSourceFactory = { repository.getCachePagination() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = repository.getPagingPokemon(),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData -> pagingData.map { it.mapToDomainPaging() } }
    }

    override fun getDetailPokemon(url: String): Flow<PokemonDetail> {
        return repository.getDetailPokemon(url)
    }

    override fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies> {
        return repository.getDetailSpeciesPokemon(url)
    }
}