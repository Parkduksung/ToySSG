package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.toyssg.util.Result
import javax.inject.Inject

class SSGRemoteDataSourceImpl @Inject constructor(private val ssgApi: SSGApi) :
    SSGRemoteDataSource {
    override suspend fun getSSGItemResponse(): Result<SSGItemResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response =
                    ssgApi.getSSGItemResponse().execute().body()
                Result.Success(response!!)
            } catch (e: Exception) {
                Result.Error(Exception("Error GetSSGItemResponse!"))
            }
        }
}