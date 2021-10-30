package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGItemResponse

class SSGRemoteDataSourceImpl(private val ssgApi: SSGApi) : SSGRemoteDataSource {
    fun getSSGItemResponse(): Result<SSGItemResponse> {
        TODO("Not yet implemented")
    }
}