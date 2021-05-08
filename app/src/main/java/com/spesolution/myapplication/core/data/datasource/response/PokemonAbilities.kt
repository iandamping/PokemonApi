package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class PokemonAbilitiesResponse(
    @SerializedName("ability") val abilities: PokemonAbilitiesNameResponse)

data class PokemonAbilitiesNameResponse(
    @SerializedName("name") val abilityName: String
)