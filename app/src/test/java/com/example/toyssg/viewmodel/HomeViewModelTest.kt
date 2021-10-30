package com.example.toyssg.viewmodel

import base.ViewModelBaseTest
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.data.repo.SSGRepositoryImpl
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest.Companion.mockSSGItemResponse
import com.example.toyssg.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class HomeViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var ssgRepository: SSGRepository

    private lateinit var homeViewModel: HomeViewModel

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        ssgRepository = Mockito.mock(SSGRepositoryImpl::class.java)
        homeViewModel = HomeViewModel(application, ssgRepository)
        homeViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }

    @Test
    fun checkGetSSGItemResponseSuccessTest() = runBlocking {

        val mockDataList = emptyList<SSGData>()

        val successResult =
            Result.Success(mockSSGItemResponse(mockResult = "200", mockData = mockDataList))

        Mockito.`when`(ssgRepository.getSSGItemResponse()).thenReturn(successResult)

        homeViewModel.getSSGItemResponse()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.GetSSGItemList(mockDataList))

    }


    @Test
    fun checkGetSSGItemResponseFailTest() = runBlocking {

        val exception = Exception("Error GetSSGItemResponse!")

        val failResult = Result.Error(exception)

        Mockito.`when`(ssgRepository.getSSGItemResponse()).thenReturn(failResult)

        homeViewModel.getSSGItemResponse()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error(exception.message.toString()))

    }
}