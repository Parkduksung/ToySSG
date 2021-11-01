package com.example.toyssg.data.source.local

import com.example.toyssg.room.SSGEntity
import com.example.toyssg.util.Result

interface SSGLocalDataSource {

    suspend fun registerSSGEntity(entity: SSGEntity): Boolean

    suspend fun isExistSSGEntityList(): Boolean

    suspend fun getAllSSGEntity(): Result<List<SSGEntity>>

    suspend fun deleteSSGEntity(entity: SSGEntity) : Boolean
}