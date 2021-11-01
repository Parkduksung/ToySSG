package com.example.toyssg.data.source.local

import com.example.toyssg.room.SSGDao
import com.example.toyssg.room.SSGEntity
import com.example.toyssg.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SSGLocalDataSourceImpl @Inject constructor(private val ssgDao: SSGDao) : SSGLocalDataSource {

    override suspend fun registerSSGEntity(entity: SSGEntity): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext try {
                ssgDao.registerSSGEntity(entity) > 0
            } catch (e: Exception) {
                false
            }
        }

    override suspend fun isExistSSGEntityList(): Boolean = withContext(Dispatchers.IO) {
        return@withContext ssgDao.getAll().isNotEmpty()
    }

    override suspend fun getAllSSGEntity(): Result<List<SSGEntity>> = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(ssgDao.getAll())
        } catch (e: Exception) {
            Result.Error(Exception("Error getAllSSGEntity!"))
        }
    }

    override suspend fun deleteSSGEntity(entity: SSGEntity): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            ssgDao.deleteSSGEntity(entity) >= 1
        } catch (e: Exception) {
            false
        }
    }
}