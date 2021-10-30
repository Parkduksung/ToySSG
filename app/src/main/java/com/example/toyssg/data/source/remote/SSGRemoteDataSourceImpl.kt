package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SSGRemoteDataSourceImpl(private val ssgApi: SSGApi) : SSGRemoteDataSource {
    override suspend fun getSSGItemResponse(): Result<SSGItemResponse> =
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
}