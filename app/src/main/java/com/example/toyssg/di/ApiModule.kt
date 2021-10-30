package com.example.toyssg.di

import com.example.toyssg.api.SSGApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL_SSG = "http://static.ssgcdn.com/"

    @Singleton
    @Provides
    fun provideSSGApi(): SSGApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SSG)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SSGApi::class.java)
    }
}