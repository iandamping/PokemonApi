package com.spesolution.myapplication.core.data.datasource.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_pokemon")
data class PokemonRemoteKeysEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pokemon_remote_key_id") val pokeId: Long?,
        @ColumnInfo(name = "pokemon_remote_prev_key") val prevKey: Int?,
        @ColumnInfo(name = "pokemon_remote_next_key") val nextKey: Int?
)
