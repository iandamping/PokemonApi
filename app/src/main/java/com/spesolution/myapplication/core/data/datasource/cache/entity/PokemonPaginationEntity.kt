package com.spesolution.myapplication.core.data.datasource.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "pagination_pokemon")
data class PokemonPaginationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pokemon_id") var pokemonId: Long?,
    @ColumnInfo(name = "pokemon_url") val pokemonUrl: String,
    @ColumnInfo(name = "pokemon_name") val pokemonName: String,
    @ColumnInfo(name = "pokemon_image") val pokemonImage: String
)
