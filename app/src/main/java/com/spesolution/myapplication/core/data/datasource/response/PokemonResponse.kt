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
    @SerializedName("sprites") val pokemonImage:PokemonSprites
)

data class PokemonSprites(@SerializedName("other") val sprites: PokemonSpritesOther)

data class PokemonSpritesOther(@SerializedName("official-artwork") val other: PokemonOfficialArtwork)

data class PokemonOfficialArtwork(@SerializedName("front_default") val image: String)

