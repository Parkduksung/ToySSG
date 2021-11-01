package com.example.toyssg.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SSGEntity::class], version = 1)
abstract class SSGDatabase : RoomDatabase() {

    abstract fun ssgDao(): SSGDao
}