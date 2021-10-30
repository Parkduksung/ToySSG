package com.example.toyssg.data.repo

import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.util.Result

interface SSGRepository {

    suspend fun getSSGItemResponse(): Result<SSGItemResponse>
}