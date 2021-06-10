package com.spesolution.myapplication.core.data.datasource.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "favorite_pokemon")
data class PokemonFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_favorite_id") val pokemonFavoriteId: Int?,
    @ColumnInfo(name = "pokemon_name") val pokemonName: String,
    @ColumnInfo(name = "pokemon_weight") val pokemonWeight: Int,
    @ColumnInfo(name = "pokemon_height") val pokemonHeight: Int,
    @ColumnInfo(name = "pokemon_image") val pokemonImage: String,
    @ColumnInfo(name = "pokemon_ability1") val pokemonAbility1: String,
    @ColumnInfo(name = "pokemon_ability2") val pokemonAbility2: String?,
    @ColumnInfo(name = "pokemon_small_image1") val pokemonSmallImage1: String,
    @ColumnInfo(name = "pokemon_small_image2") val pokemonSmallImage2: String,
    @ColumnInfo(name = "pokemon_small_image3") val pokemonSmallImage3: String,
    @ColumnInfo(name = "pokemon_small_image4") val pokemonSmallImage4: String,
    @ColumnInfo(name = "pokemon_stat_name0") val pokemonStatName0: String,
    @ColumnInfo(name = "pokemon_stat_point0") val pokemonStatPoint0: Int,
    @ColumnInfo(name = "pokemon_stat_name1") val pokemonStatName1: String,
    @ColumnInfo(name = "pokemon_stat_point1") val pokemonStatPoint1: Int,
    @ColumnInfo(name = "pokemon_stat_name2") val pokemonStatName2: String,
    @ColumnInfo(name = "pokemon_stat_point2") val pokemonStatPoint2: Int,
    @ColumnInfo(name = "pokemon_stat_name3") val pokemonStatName3: String,
    @ColumnInfo(name = "pokemon_stat_point3") val pokemonStatPoint3: Int,
    @ColumnInfo(name = "pokemon_stat_name4") val pokemonStatName4: String,
    @ColumnInfo(name = "pokemon_stat_point4") val pokemonStatPoint4: Int,
    @ColumnInfo(name = "pokemon_stat_name5") val pokemonStatName5: String,
    @ColumnInfo(name = "pokemon_stat_point5") val pokemonStatPoint5: Int,
    @ColumnInfo(name = "pokemon_type0") val pokemonType0: String,
    @ColumnInfo(name = "pokemon_type1") val pokemonType1: String?,
    @ColumnInfo(name = "pokemon_species_url")val pokemonSpeciesUrl:String
)