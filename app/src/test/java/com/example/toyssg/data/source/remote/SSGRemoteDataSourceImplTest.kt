package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import com.example.toyssg.api.response.SSGItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.Request
import okio.Timeout
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class SSGRemoteDataSourceImplTest {


    @Mock
    lateinit var ssgApi: SSGApi


    private lateinit var ssgRemoteDataSourceImpl: SSGRemoteDataSourceImpl

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        ssgApi = Mockito.mock(SSGApi::class.java)
        ssgRemoteDataSourceImpl = SSGRemoteDataSourceImpl(ssgApi)
    }

    @Test
    fun checkGetSSGItemSuccessTest() {

    }

    @Test
    fun checkGetSSGItemFailTest() {

    }

    fun initMockSSGApi(response: SSGItemResponse) {

        Mockito.`when`(ssgApi.getSSGItem()).thenReturn(
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
}