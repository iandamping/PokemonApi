package com.spesolution.myapplication.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spesolution.myapplication.core.data.datasource.PokemonRemoteDataSource
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.NETWORK_PAGE_SIZE
import com.spesolution.myapplication.core.domain.PokemonRepository
import com.spesolution.myapplication.core.domain.model.mapToDetail
import com.spesolution.myapplication.core.domain.model.mapToSpeciesDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) :
    PokemonRepository {

    override fun getPagingPokemon(): Flow<PagingData<PokemonPaging>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { remoteDataSource.getPagingPokemon() }
        ).flow
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
}

