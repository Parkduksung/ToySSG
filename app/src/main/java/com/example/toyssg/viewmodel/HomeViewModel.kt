package com.example.toyssg.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.data.repo.SSGRepository
import com.example.toyssg.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val ssgRepository: SSGRepository) : ViewModel() {


    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData


    suspend fun getSSGItemResponse() {
        when (val result = ssgRepository.getSSGItemResponse()) {

            is Result.Success -> {
                withContext(Dispatchers.Main) {
                    _viewStateLiveData.value = HomeViewState.GetSSGItemList(result.data.data)
                }
            }
            is Result.Error -> {
                withContext(Dispatchers.Main) {
                    _viewStateLiveData.value =
                        HomeViewState.Error(result.exception.message.toString())
                }
            }
        }
    }


    sealed class HomeViewState : ViewState {
        data class GetSSGItemList(val list: List<SSGData>) : HomeViewState()
        data class Error(val message: String) : HomeViewState()
    }

}

interface ViewState