package com.example.toyssg.viewmodel

import androidx.lifecycle.Observer
import base.BaseTest
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.data.repo.SSGRepositoryImpl
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import com.example.toyssg.util.Result

class HomeViewModelTest : BaseTest() {

    @Mock
    lateinit var ssgRepository: SSGRepository

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var viewStateObserver: Observer<ViewState>

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        ssgRepository = Mockito.mock(SSGRepositoryImpl::class.java)
        homeViewModel = HomeViewModel(ssgRepository)
        homeViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }

    @Test
    fun checkGetSSGItemResponseSuccessTest() = runBlocking {

        val successResult =
            Result.Success(SSGRemoteDataSourceImplTest.mockSSGItemResponse(mockResult = "200"))

        Mockito.`when`(ssgRepository.getSSGItemResponse()).thenReturn(successResult)

        homeViewModel.getSSGItemResponse()

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.GetSSGItemList(successResult.data.data))

    }

    @Test
    fun checkGetSSGItemResponseFailTest() = runBlocking {

        val failResult = Result.Error(Exception("Error GetSSGItemResponse!"))

        Mockito.`when`(ssgRepository.getSSGItemResponse()).thenReturn(failResult)

        homeViewModel.getSSGItemResponse()

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error(failResult.exception.message.toString()))

    }

}