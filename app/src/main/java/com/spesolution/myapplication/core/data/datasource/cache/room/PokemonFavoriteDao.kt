package com.spesolution.myapplication.core.data.datasource.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface PokemonFavoriteDao {

    @Query("SELECT * FROM favorite_pokemon")
    fun loadPokemon(): Flow<List<PokemonFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(data: PokemonFavoriteEntity)

    @Query("DELETE FROM favorite_pokemon where pokemon_favorite_id = :selectedId")
    suspend fun deleteFavoritePokemonById(selectedId: Int)
}