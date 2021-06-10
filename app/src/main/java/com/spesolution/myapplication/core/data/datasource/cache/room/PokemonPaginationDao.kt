package com.spesolution.myapplication.core.data.datasource.cache.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonPaginationEntity

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface PokemonPaginationDao {
    @Query("SELECT * FROM pagination_pokemon")
    fun loadPokemon(): PagingSource<Int, PokemonPaginationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg data: PokemonPaginationEntity)

    @Query("DELETE FROM pagination_pokemon")
    suspend fun deleteAllPokemon()
}

