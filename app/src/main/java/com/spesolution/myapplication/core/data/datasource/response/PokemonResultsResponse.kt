package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

data class PokemonResultsResponse(
    @SerializedName("url") val pokemonUrl: String
)