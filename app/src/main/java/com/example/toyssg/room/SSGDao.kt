package com.example.toyssg.room

import androidx.room.*

@Dao
interface SSGDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerSSGEntity(entity: SSGEntity): Long

    @Query("SELECT * FROM ssg_table")
    fun getAll(): List<SSGEntity>

    @Query("DELETE FROM ssg_table WHERE name = (:name) And price = (:price) AND detail = (:detail) AND image = (:image)")
    fun deleteSSGEntity(name: String?, price: String?, detail: String?, image: String?): Int

}