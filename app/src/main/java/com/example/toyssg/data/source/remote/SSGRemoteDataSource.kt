package com.example.toyssg.data.source.remote

import com.example.toyssg.api.response.SSGItemResponse

interface SSGRemoteDataSource {
    suspend fun getSSGItemResponse(): Result<SSGItemResponse>
}