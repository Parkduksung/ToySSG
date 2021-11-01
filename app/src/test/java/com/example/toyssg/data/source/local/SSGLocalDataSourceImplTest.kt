package com.example.toyssg.data.source.local

import base.BaseTest
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest.Companion.mockSSGDataList
import com.example.toyssg.room.SSGDao
import com.example.toyssg.room.SSGEntity
import com.example.toyssg.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class SSGLocalDataSourceImplTest : BaseTest() {


    @Mock
    lateinit var ssgDao: SSGDao


    private lateinit var ssgLocalDataSourceImpl: SSGLocalDataSourceImpl

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        ssgDao = Mockito.mock(SSGDao::class.java)
        ssgLocalDataSourceImpl = SSGLocalDataSourceImpl(ssgDao)
    }


    @Test
    fun checkGetAllSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntityList = mockSSGDataList.map { it.item.toSSGEntity() }

        val successResult = Result.Success(mockSSGEntityList)

        Mockito.`when`(ssgDao.getAll()).thenReturn(successResult.data)

        MatcherAssert.assertThat(
            (ssgLocalDataSourceImpl.getAllSSGEntity() as Result.Success).data,
            Matchers.`is`(successResult.data)
        )
    }

    @Test
    fun checkGetAllSSGEntityFailTest() = runBlocking {

        val exception =
            Exception("Error getAllSSGEntity!")

        val failureResult = Result.Error(exception)

        Mockito.`when`(ssgDao.getAll()).then { failureResult }

        MatcherAssert.assertThat(
            (ssgLocalDataSourceImpl.getAllSSGEntity() as Result.Error).exception.message,
            Matchers.`is`(failureResult.exception.message)
        )
    }


    @Test
    fun checkExistSSGEntityListSuccessTest() = runBlocking {

        val mockSSGEntityList = mockSSGDataList.map { it.item.toSSGEntity() }

        val successResult = Result.Success(mockSSGEntityList)

        Mockito.`when`(ssgDao.getAll()).thenReturn(successResult.data)


        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.isExistSSGEntityList(),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkExistSSGEntityListFailTest() = runBlocking {

        val mockSSGEntityList = emptyList<SSGEntity>()

        val successResult = Result.Success(mockSSGEntityList)

        Mockito.`when`(ssgDao.getAll()).thenReturn(successResult.data)


        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.isExistSSGEntityList(),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkRegisterSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntity = mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgDao.registerSSGEntity(mockSSGEntity)).thenReturn(1)

        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.registerSSGEntity(mockSSGEntity),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkRegisterSSGEntityFailTest() = runBlocking {

        val mockSSGEntity = mockSSGDataList[0].item.toSSGEntity()

        val exception = Exception()

        val failureResult = Result.Error(exception)

        Mockito.`when`(ssgDao.registerSSGEntity(mockSSGEntity)).then { failureResult }

        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.registerSSGEntity(mockSSGEntity),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkDeleteSSGEntitySuccessTest() = runBlocking {

        val mockSSGEntity = mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgDao.deleteSSGEntity(mockSSGEntity)).thenReturn(1)

        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.deleteSSGEntity(mockSSGEntity),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkDeleteSSGEntityFailTest() = runBlocking {

        val mockSSGEntity = mockSSGDataList[0].item.toSSGEntity()

        val exception = Exception()

        val failureResult = Result.Error(exception)

        Mockito.`when`(ssgDao.deleteSSGEntity(mockSSGEntity)).then { failureResult }

        MatcherAssert.assertThat(
            ssgLocalDataSourceImpl.deleteSSGEntity(mockSSGEntity),
            Matchers.`is`(false)
        )
    }
}