package com.example.toyssg.data.source.remote

import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.util.Result

interface SSGRemoteDataSource {
    suspend fun getSSGItemResponse(): Result<SSGItemResponse>
}