package com.spesolution.myapplication.core.domain.model

import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.response.PokemonAbilitiesResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonBasicStatsResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonDetailResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonSpeciesDetailResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonSpeciesEggGroupResponse
import com.spesolution.myapplication.core.data.datasource.response.PokemonTypesResponse
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import com.spesolution.myapplication.core.domain.response.PokemonStat
import com.spesolution.myapplication.util.PokemonConstant.ONE_EGG_MONS
import com.spesolution.myapplication.util.PokemonConstant.ONE_SKILL_MONS
import com.spesolution.myapplication.util.PokemonConstant.ONE_TYPE_MONS

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun PokemonDetailResponse.mapToDetail(): PokemonDetail = PokemonDetail(
    pokemonWeight = pokemonWeight,
    pokemonHeight = pokemonHeight,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage.sprites.other.image,
    pokemonSmallImage1 = pokemonImage.smallImage1,
    pokemonSmallImage2 = pokemonImage.smallImage2,
    pokemonSmallImage3 = pokemonImage.smallImage3,
    pokemonSmallImage4 = pokemonImage.smallImage4,
    pokemonStat0 = pokemonStats[0].mapToDetail(),
    pokemonStat1 = pokemonStats[1].mapToDetail(),
    pokemonStat2 = pokemonStats[2].mapToDetail(),
    pokemonStat3 = pokemonStats[3].mapToDetail(),
    pokemonStat4 = pokemonStats[4].mapToDetail(),
    pokemonStat5 = pokemonStats[5].mapToDetail(),
    pokemonType0 = pokemonTypes[0].type.typeName,
    pokemonType1 = pokemonTypes.checkTypeList(1, 1),
    pokemonAbility1 = pokemonAbilities[0].abilities.abilityName,
    pokemonAbility2 = pokemonAbilities.checkAbilitiesList(1, 1),
    pokemonSpeciesUrl = pokemonSpecies.speciesUrl
)

fun PokemonDetailResponse.mapToFavoriteDatabase(): PokemonFavoriteEntity = PokemonFavoriteEntity(
    pokemonFavoriteId = null,
    pokemonWeight = pokemonWeight,
    pokemonHeight = pokemonHeight,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage.sprites.other.image,
    pokemonSmallImage1 = pokemonImage.smallImage1,
    pokemonSmallImage2 = pokemonImage.smallImage2,
    pokemonSmallImage3 = pokemonImage.smallImage3,
    pokemonSmallImage4 = pokemonImage.smallImage4,
    pokemonStatName0 = pokemonStats[0].statName.name,
    pokemonStatName1 = pokemonStats[1].statName.name,
    pokemonStatName2 = pokemonStats[2].statName.name,
    pokemonStatName3 = pokemonStats[3].statName.name,
    pokemonStatName4 = pokemonStats[4].statName.name,
    pokemonStatName5 = pokemonStats[5].statName.name,
    pokemonStatPoint0 = pokemonStats[0].baseStat,
    pokemonStatPoint1 = pokemonStats[1].baseStat,
    pokemonStatPoint2 = pokemonStats[2].baseStat,
    pokemonStatPoint3 = pokemonStats[3].baseStat,
    pokemonStatPoint4 = pokemonStats[4].baseStat,
    pokemonStatPoint5 = pokemonStats[5].baseStat,
    pokemonType0 = pokemonTypes[0].type.typeName,
    pokemonType1 = pokemonTypes[1].type.typeName,
    pokemonAbility1 = pokemonAbilities[0].abilities.abilityName,
    pokemonAbility2 = pokemonAbilities[1].abilities.abilityName,
    pokemonSpeciesUrl = pokemonSpecies.speciesUrl
)

fun PokemonDetailResponse.mapToPaging(url: String): PokemonPaging = PokemonPaging(
    pokemonUrl = url,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage.sprites.other.image
)

fun PokemonPaging.mapToPagingDatabase():PokemonPaginationEntity = PokemonPaginationEntity(
    pokemonId = null,
    pokemonUrl = pokemonUrl,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage
)

fun PokemonPaginationEntity.mapToDomainPaging():PokemonPaging = PokemonPaging(
    pokemonUrl = pokemonUrl,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage
)

fun PokemonBasicStatsResponse.mapToDetail(): PokemonStat = PokemonStat(
    baseStat, statName.name
)

fun List<PokemonTypesResponse>.checkTypeList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].type.typeName
    } else ONE_TYPE_MONS

fun List<PokemonAbilitiesResponse>.checkAbilitiesList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].abilities.abilityName
    } else ONE_SKILL_MONS

fun PokemonSpeciesDetailResponse.mapToSpeciesDetail(): PokemonDetailSpecies = PokemonDetailSpecies(
    happines = pokemonHappines,
    captureRate = pokemonCaptureRate,
    color = pokemonColor.pokemonColor,
    eggGroup1 = pokemonEggGroup[0].eggName,
    eggGroup2 = pokemonEggGroup.checkEggGroupList(1, 1),
    generation = pokemonGeneration.pokemonGenerationLString,
    growthRate = pokemonGrowthRate.pokemonGrowthRate,
    habitat = pokemonHabitat.pokemonHabitat,
    shape = pokemonShape.pokemonShape
)

fun List<PokemonSpeciesEggGroupResponse>.checkEggGroupList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].eggName
    } else ONE_EGG_MONS