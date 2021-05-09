package com.spesolution.myapplication.core.data.datasource

import androidx.paging.PagingSource
import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResultsResponse
import com.spesolution.myapplication.core.data.model.DataSourceResult
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRemoteDataSource {

    fun getPagingPokemon(): PagingSource<Int, PokemonPaging>

    suspend fun getDetailPokemon(url: String): PokemonResponse
}