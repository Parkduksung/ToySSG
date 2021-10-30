package com.example.toyssg.data.repo

import com.example.toyssg.data.source.remote.SSGRemoteDataSource
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SSGRepositoryImplTest {

    @Mock
    lateinit var ssgRemoteDataSource: SSGRemoteDataSource


    private lateinit var ssgRepositoryImpl: SSGRepositoryImpl


    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        ssgRemoteDataSource = Mockito.mock(SSGRemoteDataSourceImpl::class.java)
        ssgRepositoryImpl = SSGRepositoryImpl(ssgRemoteDataSource)
    }

    @Test
    fun checkGetSSGItemResponseSuccessTest() = runBlocking {

    }

    @Test
    fun checkGetSSGItemResponseFailTest() = runBlocking {

    }

}