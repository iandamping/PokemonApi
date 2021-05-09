package com.spesolution.myapplication.core.domain.response

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonDetail(
    val pokemonName:String,
    val pokemonWeight:Int,
    val pokemonHeight:Int,
    val pokemonImage:String,
    val pokemonAbility1:String,
    val pokemonAbility2:String,
    val pokemonSmallImage1:String,
    val pokemonSmallImage2:String,
    val pokemonSmallImage3:String,
    val pokemonSmallImage4:String,
    val pokemonStat0: PokemonStat,
    val pokemonStat1: PokemonStat,
    val pokemonStat2: PokemonStat,
    val pokemonStat3: PokemonStat,
    val pokemonStat4: PokemonStat,
    val pokemonStat5: PokemonStat,
    val pokemonType0:String,
    val pokemonType1:String,
)
