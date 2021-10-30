package com.example.toyssg.viewmodel

import android.app.Application
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.base.BaseViewModel
import com.example.toyssg.base.ViewState
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.ext.ioScope
import com.example.toyssg.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val ssgRepository: SSGRepository
) : BaseViewModel(app) {

    fun getSSGItemResponse() {
        ioScope {
            when (val result = ssgRepository.getSSGItemResponse()) {

                is Result.Success -> {
                    viewStateChanged(HomeViewState.GetSSGItemList(result.data.data))
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
