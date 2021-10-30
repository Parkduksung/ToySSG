package com.example.toyssg.data.repo

import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.data.source.remote.SSGRemoteDataSource
import com.example.toyssg.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SSGRepositoryImpl(private val ssgRemoteDataSource: SSGRemoteDataSource) : SSGRepository {
    override suspend fun getSSGItemResponse(): Result<SSGItemResponse> =
        withContext(Dispatchers.IO) {
            return@withContext ssgRemoteDataSource.getSSGItemResponse()
        }
}