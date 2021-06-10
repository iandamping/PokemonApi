package com.spesolution.myapplication.module

import android.content.Context
import androidx.room.Room
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonDatabase
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonFavoriteDao
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonPaginationDao
import com.spesolution.myapplication.core.data.datasource.cache.room.PokemonRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): PokemonDatabase {
        return Room
            .databaseBuilder(context, PokemonDatabase::class.java, "pokemon.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePaginationPokemonDao(db: PokemonDatabase): PokemonPaginationDao {
        return db.paginationDao()
    }

    @Provides
    fun provideFavoritePokemonDao(db: PokemonDatabase): PokemonFavoriteDao {
        return db.favoriteDao()
    }

    @Provides
    fun provideRemoteKeyDao(db: PokemonDatabase): PokemonRemoteKeyDao {
        return db.remoteKeyDao()
    }
}