package com.spesolution.myapplication.core.domain.model

import com.spesolution.myapplication.core.data.datasource.response.PokemonBasicStatsResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonTypeSingleResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonTypesResponse
import com.spesolution.myapplication.util.PokemonConstant.ONE_TYPE_MONS

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun PokemonResponse.mapToDomain(): Pokemon = Pokemon(
    pokemonWeight = pokemonWeight,
    pokemonHeight = pokemonHeight,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage.sprites.other.image,
    pokemonSmallImage1 = pokemonImage.smallImage1,
    pokemonSmallImage2 = pokemonImage.smallImage2,
    pokemonSmallImage3 = pokemonImage.smallImage3,
    pokemonSmallImage4 = pokemonImage.smallImage4,
    pokemonStat0 = pokemonStats[0].mapToDomain(),
    pokemonStat1 = pokemonStats[1].mapToDomain(),
    pokemonStat2 = pokemonStats[2].mapToDomain(),
    pokemonStat3 = pokemonStats[5].mapToDomain(),
    pokemonType0 = pokemonTypes[0].type.typeName,
    pokemonType1 = pokemonTypes.checkList(1,1),

    )

fun PokemonBasicStatsResponse.mapToDomain(): PokemonStat = PokemonStat(
    baseStat, statName.name
)

fun List<PokemonTypesResponse>.checkList(size: Int,position:Int): String =
    if (this.size > size) {
        this[position].type.typeName
    } else ONE_TYPE_MONS