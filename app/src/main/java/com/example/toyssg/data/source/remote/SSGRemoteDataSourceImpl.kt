package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SSGRemoteDataSourceImpl(private val ssgApi: SSGApi) : SSGRemoteDataSource {
    override suspend fun getSSGItemResponse(): Result<SSGItemResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response =
                    ssgApi.getSSGItem().execute().body()
                Result.success(response!!)
            } catch (e: Exception) {
                Result.failure<SSGItemResponse>(Exception("Error GetSSGItemResponse!"))
            }
        }
}