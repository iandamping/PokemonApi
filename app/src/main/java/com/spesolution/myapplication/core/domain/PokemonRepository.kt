package com.spesolution.myapplication.core.domain

import androidx.paging.PagingData
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRepository {

    fun getPagingPokemon(): Flow<PagingData<PokemonPaging>>

    fun getDetailPokemon(url:String): Flow<DomainResult<PokemonDetail>>
}