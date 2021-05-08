package com.spesolution.myapplication.core.data.datasource.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonSpritesResponse(
    @SerializedName("other") val sprites: PokemonSpritesOtherResponse,
    @SerializedName("back_default") val smallImage1: String,
    @SerializedName("back_shiny") val smallImage2: String,
    @SerializedName("front_default") val smallImage3: String,
    @SerializedName("front_shiny") val smallImage4: String,
)

data class PokemonSpritesOtherResponse(@SerializedName("official-artwork") val other: PokemonOfficialArtworkResponse)

data class PokemonOfficialArtworkResponse(@SerializedName("front_default") val image: String)

