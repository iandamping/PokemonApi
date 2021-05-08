package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonResponse(
    @SerializedName("id") val pokemonId: Int,
    @SerializedName("name") val pokemonName: String,
    @SerializedName("weight") val pokemonWeight: Int,
    @SerializedName("height") val pokemonHeight: Int,
    @SerializedName("sprites") val pokemonImage: PokemonSpritesResponse,
    @SerializedName("stats") val pokemonStats: List<PokemonBasicStatsResponse>,
    @SerializedName("types") val pokemonTypes: List<PokemonTypesResponse>,
    @SerializedName("abilities") val pokemonAbilities: List<PokemonAbilitiesResponse>,
)
