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
    @SerializedName("sprites") val pokemonImage: PokemonSprites
)

data class PokemonSprites(
    @SerializedName("other") val sprites: PokemonSpritesOther,
    @SerializedName("back_default") val smallImage1: String,
    @SerializedName("back_shiny") val smallImage2: String,
    @SerializedName("front_default") val smallImage3: String,
    @SerializedName("front_shiny") val smallImage4: String,
)

data class PokemonSpritesOther(@SerializedName("official-artwork") val other: PokemonOfficialArtwork)

data class PokemonOfficialArtwork(@SerializedName("front_default") val image: String)

