package com.example.toyssg.di

import android.content.Context
import androidx.room.Room
import com.example.toyssg.room.SSGDao
import com.example.toyssg.room.SSGDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideSSGDao(ssgDatabase: SSGDatabase): SSGDao {
        return ssgDatabase.ssgDao()
    }

    @Provides
    @Singleton
    fun provideSSGDatabase(@ApplicationContext appContext: Context): SSGDatabase {
        return Room.databaseBuilder(
            appContext,
            SSGDatabase::class.java,
            "ssg_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}