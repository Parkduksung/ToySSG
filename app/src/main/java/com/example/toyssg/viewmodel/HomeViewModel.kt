package com.example.toyssg.viewmodel

import android.app.Application
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.api.response.SSGItem
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
            showProgress()
            when (val result = ssgRepository.getSSGItemResponse()) {
                is Result.Success -> {
                    val resultResponse = result.data
                    viewStateChanged(HomeViewState.GetSSGItemList(resultResponse.dataList))
                    hideProgress()
                }
                is Result.Error -> {
                    viewStateChanged(HomeViewState.Error(result.exception.message.toString()))
                    hideProgress()
                }
            }
        }
    }

    fun addCurrentSSGItem(item: SSGItem) {
        ioScope.launch {
            if (ssgRepository.registerSSGEntity(item.toSSGEntity())) {
                viewStateChanged(HomeViewState.AddCurrentItem)
            } else {
                viewStateChanged(HomeViewState.Error("AddCurrentSSGItem Error"))
            }
        }
    }

    fun deleteCurrentSSGItem(item: SSGItem) {
        ioScope.launch {
            if (ssgRepository.deleteSSGEntity(item.toSSGEntity())) {
                viewStateChanged(HomeViewState.DeleteCurrentItem(item))
            } else {
                viewStateChanged(HomeViewState.Error("DeleteCurrentSSGItem Error"))
            }
        }
    }

    fun getCurrentItemList() {
        ioScope.launch {
            when (val result = ssgRepository.getAllSSGEntity()) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        val toSSGItemList = result.data.map { it.toSSGItem() }
                        viewStateChanged(HomeViewState.GetCurrentItemList(toSSGItemList))
                    } else {
                        viewStateChanged(HomeViewState.EmptyCurrentItem)
                    }
                }

                is Result.Error -> {
                    viewStateChanged(HomeViewState.Error(result.exception.message.toString()))
                }
            }
        }
    }


    fun routeContent() {
        viewStateChanged(HomeViewState.RouteContent)
    }

    fun routeDetail(item: SSGItem) {
        viewStateChanged(HomeViewState.RouteDetail(item = item))
    }

    fun routeCurrent() {
        viewStateChanged(HomeViewState.RouteCurrent)
    }

    private fun showProgress() {
        viewStateChanged(HomeViewState.ShowProgress)
    }

    private fun hideProgress() {
        viewStateChanged(HomeViewState.HideProgress)
    }


    sealed class HomeViewState : ViewState {
        data class GetSSGItemList(val list: List<SSGData>) : HomeViewState()
        data class Error(val message: String) : HomeViewState()
        data class RouteDetail(val item: SSGItem) : HomeViewState()
        data class GetCurrentItemList(val list: List<SSGItem>) : HomeViewState()
        object RouteContent : HomeViewState()
        object RouteCurrent : HomeViewState()
        object ShowProgress : HomeViewState()
        object HideProgress : HomeViewState()
        object AddCurrentItem : HomeViewState()
        data class DeleteCurrentItem(val item: SSGItem) : HomeViewState()
        object EmptyCurrentItem : HomeViewState()
    }

}
