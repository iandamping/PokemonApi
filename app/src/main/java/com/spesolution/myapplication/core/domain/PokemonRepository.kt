package com.spesolution.myapplication.core.domain

import androidx.paging.PagingData
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRepository {

    fun getPokemon(): Flow<DomainResult<List<Pokemon>>>

    fun getPagingPokemon(): Flow<PagingData<Pokemon>>
}