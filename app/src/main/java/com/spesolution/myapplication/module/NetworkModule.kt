package com.spesolution.myapplication.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spesolution.myapplication.core.data.datasource.remote.ApiInterface
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.BASE_URL
import com.spesolution.myapplication.core.data.datasource.remote.NetworkConstant.cacheSize
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClientCache(@ApplicationContext context: Context): Cache = Cache(context.cacheDir, cacheSize)


    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            with(chain) { proceed(this.request().newBuilder().build()) }
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiInterface = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)
}