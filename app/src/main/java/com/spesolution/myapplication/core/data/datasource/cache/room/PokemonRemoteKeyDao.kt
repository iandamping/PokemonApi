package com.spesolution.myapplication.core.data.datasource.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spesolution.myapplication.core.data.datasource.cache.entity.PokemonRemoteKeysEntity

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface PokemonRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(vararg remoteKey: PokemonRemoteKeysEntity)

    @Query("SELECT * FROM remote_keys_pokemon WHERE pokemon_remote_key_id = :repoId")
    suspend fun remoteKeysRepoId(repoId: Long): PokemonRemoteKeysEntity?

    @Query("DELETE FROM remote_keys_pokemon")
    suspend fun clearRemoteKeys()
}