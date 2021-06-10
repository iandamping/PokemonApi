package com.spesolution.myapplication.core.data.datasource.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonRemoteKeysEntity

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Database(
    entities = [PokemonPaginationEntity::class, PokemonFavoriteEntity::class, PokemonRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun paginationDao(): PokemonPaginationDao
    abstract fun favoriteDao(): PokemonFavoriteDao
    abstract fun remoteKeyDao(): PokemonRemoteKeyDao
}