package com.example.toyssg.api

import com.example.toyssg.api.response.SSGItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface SSGApi {

    @GET(HOMEWORK_URL)
    fun getSSGItemResponse(): Call<SSGItemResponse>

    companion object {
        private const val HOMEWORK_URL = "ui/app/test/homework.json"
    }
}