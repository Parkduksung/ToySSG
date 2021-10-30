package com.example.toyssg.viewmodel

import android.app.Application
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.base.BaseViewModel
import com.example.toyssg.base.ViewState
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val ssgRepository: SSGRepository
) : BaseViewModel(app) {

    fun getSSGItemResponse() {
        ioScope.launch {
            when (val result = ssgRepository.getSSGItemResponse()) {

                is Result.Success -> {
                    val resultResponse = result.data
                    viewStateChanged(HomeViewState.GetSSGItemList(resultResponse.dataList))
                }
                is Result.Error -> {
                    viewStateChanged(HomeViewState.Error(result.exception.message.toString()))
                }
            }
        }
    }


    sealed class HomeViewState : ViewState {
        data class GetSSGItemList(val list: List<SSGData>) : HomeViewState()
        data class Error(val message: String) : HomeViewState()
    }

}
