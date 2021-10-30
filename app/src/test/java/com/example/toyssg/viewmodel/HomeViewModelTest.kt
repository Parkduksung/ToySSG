package com.example.toyssg.viewmodel

import base.BaseTest
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.data.repo.SSGRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito

class HomeViewModelTest : BaseTest() {


    @Mock
    lateinit var ssgRepository: SSGRepository

    private lateinit var homeViewModel: HomeViewModel


    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        ssgRepository = Mockito.mock(SSGRepositoryImpl::class.java)
        homeViewModel = HomeViewModel(ssgRepository)
    }



}