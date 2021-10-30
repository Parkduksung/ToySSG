package com.example.toyssg.data.source.remote

import com.example.toyssg.api.SSGApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


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

}