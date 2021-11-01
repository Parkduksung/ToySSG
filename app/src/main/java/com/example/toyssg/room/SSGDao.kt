package com.example.toyssg.room

import androidx.room.*

@Dao
interface SSGDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerSSGEntity(entity: SSGEntity): Long

    @Query("SELECT * FROM ssg_table")
    fun getAll(): List<SSGEntity>

    @Delete
    fun deleteSSGEntity(eatEntity: SSGEntity): Int

}