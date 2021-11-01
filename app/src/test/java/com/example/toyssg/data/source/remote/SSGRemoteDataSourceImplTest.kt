package com.example.toyssg.data.source.remote

import base.BaseTest
import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.util.Result
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SSGRemoteDataSourceImplTest : BaseTest() {


    @Mock
    lateinit var ssgApi: SSGApi


    private lateinit var ssgRemoteDataSourceImpl: SSGRemoteDataSourceImpl

    @Before
    override fun setup() {
        super.setup()
        ssgApi = Mockito.mock(SSGApi::class.java)
        ssgRemoteDataSourceImpl = SSGRemoteDataSourceImpl(ssgApi)
    }

    @Test
    fun checkGetSSGItemResponseSuccessTest() = runBlocking {

        initMockSSGApi(mockSSGItemResponse(mockResult = "200", mockData = mockSSGDataList))

        val successResult =
            Result.Success(mockSSGItemResponse(mockResult = "200", mockData = mockSSGDataList))

        MatcherAssert.assertThat(
            "올바른 SSGItemResponse 값이 나오므로 성공",
            ((ssgRemoteDataSourceImpl.getSSGItemResponse() as Result.Success<SSGItemResponse>).data.result),
            Matchers.`is`(successResult.data.result)
        )
    }

    @Test
    fun checkGetSSGItemResponseFailTest() = runBlocking {

        val failResult = Result.Error(Exception("Error GetSSGItemResponse!"))

        Mockito.`when`(ssgApi.getSSGItemResponse())
            .then { failResult }

        MatcherAssert.assertThat(
            "Error 가 발생했으므로 실패.",
            ((ssgRemoteDataSourceImpl.getSSGItemResponse() as Result.Error).exception.message),
            Matchers.`is`(failResult.exception.message)
        )

    }

    fun initMockSSGApi(response: SSGItemResponse) {

        Mockito.`when`(ssgApi.getSSGItemResponse()).thenReturn(
            object : Call<SSGItemResponse> {
                override fun clone(): Call<SSGItemResponse> {
                    TODO("Not yet implemented")
                }

                override fun execute(): Response<SSGItemResponse> {
                    return Response.success(response)
                }

                override fun enqueue(callback: Callback<SSGItemResponse>) {
                    TODO("Not yet implemented")
                }

                override fun isExecuted(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun cancel() {
                    TODO("Not yet implemented")
                }

                override fun isCanceled(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    companion object {

        fun mockSSGItemResponse(
            mockResult: String,
            mockData: List<SSGData> = emptyList()
        ): SSGItemResponse =
            SSGItemResponse(
                dataList = mockData,
                result = mockResult
            )


        val mockSSGDataList = listOf(

            SSGData(
                viewType = "image",
                item = SSGItem(
                    image = "https://images.unsplash.com/photo-1490885578174-acda8905c2c6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80"
                )
            ),

            SSGData(
                viewType = "productItem",
                item = SSGItem(
                    image = "https://images.unsplash.com/photo-1484406566174-9da000fda645?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80",
                    price = "10000",
                    name = "상품01",
                    detail = "상품01 입니다."
                )
            )
        )
    }
}