package com.example.toyssg.viewmodel

import base.ViewModelBaseTest
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.data.repo.SSGRepositoryImpl
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest
import com.example.toyssg.data.source.remote.SSGRemoteDataSourceImplTest.Companion.mockSSGItemResponse
import com.example.toyssg.room.SSGEntity
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

    @Test
    fun checkRouteContentTest() = runBlocking {

        homeViewModel.routeContent()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.RouteContent)
    }

    @Test
    fun checkRouteCurrentTest() = runBlocking {
        homeViewModel.routeCurrent()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.RouteCurrent)
    }


    @Test
    fun checkRouteDetailTest() = runBlocking {

        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item

        homeViewModel.routeDetail(mockSSGItem)

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.RouteDetail(mockSSGItem))

    }


    @Test
    fun checkAddCurrentSSGItemSuccessTest() = runBlocking {

        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgRepository.registerSSGEntity(mockSSGEntity)).thenReturn(true)

        homeViewModel.addCurrentSSGItem(mockSSGItem)

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.AddCurrentItem)
    }

    @Test
    fun checkAddCurrentSSGItemFailTest() = runBlocking {

        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgRepository.registerSSGEntity(mockSSGEntity)).thenReturn(false)

        homeViewModel.addCurrentSSGItem(mockSSGItem)

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error("AddCurrentSSGItem Error"))
    }

    @Test
    fun checkDeleteCurrentSSGItemSuccessTest() = runBlocking {

        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgRepository.deleteSSGEntity(mockSSGEntity)).thenReturn(true)

        homeViewModel.deleteCurrentSSGItem(mockSSGItem)

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.DeleteCurrentItem(mockSSGItem))
    }

    @Test
    fun checkDeleteCurrentSSGItemFailTest() = runBlocking {
        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item

        val mockSSGEntity = SSGRemoteDataSourceImplTest.mockSSGDataList[0].item.toSSGEntity()

        Mockito.`when`(ssgRepository.deleteSSGEntity(mockSSGEntity)).thenReturn(false)

        homeViewModel.deleteCurrentSSGItem(mockSSGItem)

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error("DeleteCurrentSSGItem Error"))
    }

    @Test
    fun checkGetCurrentItemListSuccessTest() = runBlocking {

        val mockSSGItem = SSGRemoteDataSourceImplTest.mockSSGDataList.map { it.item }

        val mockSSGEntityList =
            mockSSGItem.map { it.toSSGEntity() }

        val successResult = Result.Success(mockSSGEntityList)

        Mockito.`when`(ssgRepository.getAllSSGEntity()).thenReturn(successResult)

        homeViewModel.getCurrentItemList()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.GetCurrentItemList(mockSSGItem))
    }

    @Test
    fun checkGetCurrentItemListFailTest() = runBlocking {

        val exception = Exception("Error GetCurrentItemList!")

        val failResult = Result.Error(exception)


        Mockito.`when`(ssgRepository.getAllSSGEntity()).then { failResult }

        homeViewModel.getCurrentItemList()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error(exception.message.toString()))

    }

    @Test
    fun checkGetCurrentItemListEmptyTest() = runBlocking {


        val mockEmptySSGEntityList =
            emptyList<SSGEntity>()

        val successResult = Result.Success(mockEmptySSGEntityList)

        Mockito.`when`(ssgRepository.getAllSSGEntity()).thenReturn(successResult)

        homeViewModel.getCurrentItemList()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.EmptyCurrentItem)
    }

    @Test
    fun checkIsExistCurrentItemListSuccessTest() = runBlocking {
        val mockEmptySSGEntityList =
            emptyList<SSGEntity>()

        val successResult = Result.Success(mockEmptySSGEntityList)

        Mockito.`when`(ssgRepository.getAllSSGEntity()).thenReturn(successResult)

        homeViewModel.isExistCurrentItemList()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.EmptyCurrentItem)
    }

    @Test
    fun checkIsExistCurrentItemListFailTest() = runBlocking {

        val exception = Exception("Error GetCurrentItemList!")

        val failResult = Result.Error(exception)


        Mockito.`when`(ssgRepository.getAllSSGEntity()).then { failResult }

        homeViewModel.isExistCurrentItemList()

        delay(100L)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error(exception.message.toString()))


    }

}