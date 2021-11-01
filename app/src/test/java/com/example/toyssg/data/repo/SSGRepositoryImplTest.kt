package com.example.toyssg.data.repo

import base.BaseTest
import com.example.toyssg.api.response.SSGItemResponse
import com.example.toyssg.data.source.local.SSGLocalDataSource
import com.example.toyssg.data.source.local.SSGLocalDataSourceImpl
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

    @Mock
    lateinit var ssgLocalDataSource: SSGLocalDataSource

    private lateinit var ssgRepositoryImpl: SSGRepositoryImpl


    @Before
    override fun setup() {
        super.setup()
        ssgRemoteDataSource = Mockito.mock(SSGRemoteDataSourceImpl::class.java)
        ssgLocalDataSource = Mockito.mock(SSGLocalDataSourceImpl::class.java)
        ssgRepositoryImpl = SSGRepositoryImpl(ssgRemoteDataSource, ssgLocalDataSource)
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


    @Test
    fun checkGetAllSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntityList =
            SSGRemoteDataSourceImplTest.mockSSGDataList.map { it.item.toSSGEntity() }

        val successResult = Result.Success(mockSSGEntityList)

        Mockito.`when`(ssgLocalDataSource.getAllSSGEntity()).thenReturn(successResult)

        MatcherAssert.assertThat(
            (ssgRepositoryImpl.getAllSSGEntity() as Result.Success).data,
            Matchers.`is`(successResult.data)
        )
    }

    @Test
    fun checkGetAllSSGEntityFailTest() = runBlocking {

        val exception =
            Exception("Error getAllSSGEntity!")

        val failureResult = Result.Error(exception)

        Mockito.`when`(ssgLocalDataSource.getAllSSGEntity()).then { failureResult }

        MatcherAssert.assertThat(
            (ssgRepositoryImpl.getAllSSGEntity() as Result.Error).exception.message,
            Matchers.`is`(failureResult.exception.message)
        )
    }


    @Test
    fun checkExistSSGEntityListSuccessTest() = runBlocking {

        Mockito.`when`(ssgLocalDataSource.isExistSSGEntityList()).thenReturn(true)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.isExistSSGEntityList(),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkExistSSGEntityListFailTest() = runBlocking {

        Mockito.`when`(ssgLocalDataSource.isExistSSGEntityList()).thenReturn(false)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.isExistSSGEntityList(),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkRegisterSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgLocalDataSource.registerSSGEntity(mockSSGEntity)).thenReturn(true)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.registerSSGEntity(mockSSGEntity),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkRegisterSSGEntityFailTest() = runBlocking {

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()


        Mockito.`when`(ssgLocalDataSource.registerSSGEntity(mockSSGEntity)).thenReturn(false)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.registerSSGEntity(mockSSGEntity),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkDeleteSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgLocalDataSource.deleteSSGEntity(mockSSGEntity)).thenReturn(true)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.deleteSSGEntity(mockSSGEntity),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkDeleteSSGEntityFailTest() = runBlocking {

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgLocalDataSource.deleteSSGEntity(mockSSGEntity)).thenReturn(false)

        MatcherAssert.assertThat(
            ssgRepositoryImpl.deleteSSGEntity(mockSSGEntity),
            Matchers.`is`(false)
        )
    }

}