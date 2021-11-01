package com.example.toyssg.di


import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.data.repo.SSGRepositoryImpl
import com.example.toyssg.data.source.local.SSGLocalDataSource
import com.example.toyssg.data.source.local.SSGLocalDataSourceImpl
import com.example.toyssg.data.source.remote.SSGRemoteDataSource
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSSGRepository(ssgRepositoryImpl: SSGRepositoryImpl): SSGRepository

    @Singleton
    @Binds
    abstract fun bindSSGRemoteDataSource(ssgRemoteDataSourceImpl: SSGRemoteDataSourceImpl): SSGRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindSSGLocalDataSource(ssgLocalDataSourceImpl: SSGLocalDataSourceImpl): SSGLocalDataSource

}

