package com.spesolution.myapplication.core.data.datasource.remote

import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.GET_POKEMON
import com.spesolution.myapplication.core.data.datasource.response.PokemonMainResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {

    @GET(GET_POKEMON)
    suspend fun getMainPokemon(): Response<PokemonMainResponse>

    @GET(GET_POKEMON)
    suspend fun getPaginationMainPokemon(@Query("offset") offset: Int): Response<PokemonMainResponse>

    @GET
    suspend fun getPokemon(@Url url: String): PokemonResponse
}