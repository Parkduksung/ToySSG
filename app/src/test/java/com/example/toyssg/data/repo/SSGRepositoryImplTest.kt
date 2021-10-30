package com.example.toyssg.data.repo

import base.BaseTest
import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.data.source.remote.SSGRemoteDataSource
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImpl
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest
import com.example.toyssg.util.Result
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class SSGRepositoryImplTest : BaseTest() {

    @Mock
    lateinit var ssgRemoteDataSource: SSGRemoteDataSource


    private lateinit var ssgRepositoryImpl: SSGRepositoryImpl


    @Before
    override fun setup() {
        super.setup()
        ssgRemoteDataSource = Mockito.mock(SSGRemoteDataSourceImpl::class.java)
        ssgRepositoryImpl = SSGRepositoryImpl(ssgRemoteDataSource)
    }

    @Test
    fun checkGetSSGItemResponseSuccessTest() = runBlocking {

        val successResult =
            Result.Success(SSGRemoteDataSourceImplTest.mockSSGItemResponse(mockResult = "200"))


        Mockito.`when`(ssgRemoteDataSource.getSSGItemResponse())
            .thenReturn(successResult)

        MatcherAssert.assertThat(
            "올바른 SSGItemResponse 값이 전달받았으므로 성공",
            ((ssgRepositoryImpl.getSSGItemResponse() as Result.Success<SSGItemResponse>).data.result),
            Matchers.`is`(successResult.data.result)
        )


    }

    @Test
    fun checkGetSSGItemResponseFailTest() = runBlocking {

        val failResult = Result.Error(Exception("Error GetSSGItemResponse!"))

        Mockito.`when`(ssgRemoteDataSource.getSSGItemResponse())
            .then { failResult }

        MatcherAssert.assertThat(
            "Error 가 발생했으므로 실패.",
            ((ssgRepositoryImpl.getSSGItemResponse() as Result.Error).exception.message),
            Matchers.`is`(failResult.exception.message)
        )

    }

}