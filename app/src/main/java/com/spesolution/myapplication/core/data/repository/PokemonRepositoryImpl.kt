package com.spesolution.myapplication.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spesolution.myapplication.core.data.datasource.PaginationPokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.NETWORK_PAGE_SIZE
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.domain.PokemonRepository
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.core.domain.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val pagingRemoteDataSource: PaginationPokemonRemoteDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) :
    PokemonRepository {

    override fun getPokemon(): Flow<DomainResult<List<Pokemon>>> {
        return flow {
            when (val main = remoteDataSource.getMainPokemon()) {
                is DataSourceResult.SourceValue -> {
                    val data = main.data.map { singleItem ->
                        val results = remoteDataSource.getDetailPokemon(singleItem.pokemonUrl)
                        results.mapToDomain(singleItem.pokemonUrl)
                    }
                    if (data.isNullOrEmpty()) {
                        emit(DomainResult.Error(EMPTY_DATA))
                    } else emit(DomainResult.Data(data))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(main.exception.message ?: NETWORK_ERROR))
                }

            }

        }.onStart { emit(DomainResult.Loading) }
    }

    override fun getPagingPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingRemoteDataSource }
        ).flow
    }

    override fun getDetailPokemon(url: String): Flow<DomainResult<Pokemon>> {
        return flow {
            try {
                val data = remoteDataSource.getDetailPokemon(url).mapToDomain(url)
                emit(DomainResult.Data(data))
            } catch (e: Exception) {
                emit(DomainResult.Error(e.message ?: NETWORK_ERROR))
            }

        }.catch { emit(DomainResult.Error(it.message ?: NETWORK_ERROR)) }
            .onStart { emit(DomainResult.Loading) }
    }
}

