package com.example.toyssg.data.repo

import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.data.source.local.SSGLocalDataSource
import com.example.toyssg.data.source.remote.SSGRemoteDataSource
import com.example.toyssg.room.SSGEntity
import com.example.toyssg.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SSGRepositoryImpl @Inject constructor(
    private val ssgRemoteDataSource: SSGRemoteDataSource,
    private val ssgLocalDataSource: SSGLocalDataSource
) :
    SSGRepository {
    override suspend fun getSSGItemResponse(): Result<SSGItemResponse> =
        withContext(Dispatchers.IO) {
            return@withContext ssgRemoteDataSource.getSSGItemResponse()
        }

    override suspend fun registerSSGEntity(entity: SSGEntity): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext ssgLocalDataSource.registerSSGEntity(entity)
        }

    override suspend fun isExistSSGEntityList(): Boolean = withContext(Dispatchers.IO) {
        return@withContext ssgLocalDataSource.isExistSSGEntityList()
    }

    override suspend fun getAllSSGEntity(): Result<List<SSGEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext ssgLocalDataSource.getAllSSGEntity()
        }

    override suspend fun deleteSSGEntity(entity: SSGEntity): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext ssgLocalDataSource.deleteSSGEntity(entity)
        }
}