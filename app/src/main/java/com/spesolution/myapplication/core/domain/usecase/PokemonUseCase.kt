package com.spesolution.myapplication.core.domain.usecase

import androidx.paging.PagingData
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonUseCase {

    fun getPagingPokemon(): Flow<PagingData<PokemonPaging>>

    fun getDetailPokemon(url: String): Flow<PokemonDetail>

    fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies>
}