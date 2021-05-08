package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class PokemonBasicStatsResponse(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val statName: PokemonStatNameResponse
)

data class PokemonStatNameResponse(@SerializedName("name") val name: String)