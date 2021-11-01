package com.example.toyssg.data.repo

import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.room.SSGEntity
import com.example.toyssg.util.Result

interface SSGRepository {

    suspend fun getSSGItemResponse(): Result<SSGItemResponse>

    suspend fun registerSSGEntity(entity: SSGEntity): Boolean

    suspend fun isExistSSGEntityList(): Boolean

    suspend fun getAllSSGEntity(): Result<List<SSGEntity>>

    suspend fun deleteSSGEntity(entity: SSGEntity) : Boolean
}