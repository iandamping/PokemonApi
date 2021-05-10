package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 10,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class PokemonSpeciesDetailResponse(
    @SerializedName("base_happiness") val pokemonHappines:Int,
    @SerializedName("capture_rate") val pokemonCaptureRate:Int,
    @SerializedName("color") val pokemonColor:PokemonSpeciesColorResponse,
    @SerializedName("egg_groups") val pokemonEggGroup:List<PokemonSpeciesEggGroupResponse>,
    @SerializedName("generation") val pokemonGeneration:PokemonGenerationResponse,
    @SerializedName("growth_rate") val pokemonGrowthRate:PokemonGrowthRateResponse,
    @SerializedName("habitat") val pokemonHabitat:PokemonHabitatResponse,
    @SerializedName("shape") val pokemonShape:PokemonShapeResponse,
)

data class PokemonGenerationResponse(@SerializedName("name") val pokemonGenerationLString: String)

data class PokemonGrowthRateResponse(@SerializedName("name") val pokemonGrowthRate: String)

data class PokemonHabitatResponse(@SerializedName("name") val pokemonHabitat: String)

data class PokemonShapeResponse(@SerializedName("name") val pokemonShape: String)

data class PokemonSpeciesColorResponse(@SerializedName("name") val pokemonColor:String)

data class PokemonSpeciesEggGroupResponse(@SerializedName("name") val eggName:String)