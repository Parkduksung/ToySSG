package com.example.toyssg.api

import com.example.toyssg.api.response.SSGItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface SSGApi {

    @GET("http://static.ssgcdn.com/ui/app/test/homework.json")
    fun getSSGItemResponse(): Call<SSGItemResponse>

}