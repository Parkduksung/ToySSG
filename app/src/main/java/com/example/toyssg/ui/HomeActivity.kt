package com.example.toyssg.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.toyssg.R
import com.example.toyssg.base.BaseActivity
import com.example.toyssg.databinding.HomeActBinding
import com.example.toyssg.ui.adapter.SSGAdapter
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeActBinding>(R.layout.home_act) {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val ssgAdapter by lazy { SSGAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        initViewModel()

    }

    private fun initUi() {
        binding.rvSsg.apply {
            adapter = ssgAdapter
        }
    }

    private fun initViewModel() {
        homeViewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedHomeViewState(viewState) }
        }

        homeViewModel.getSSGItemResponse()
    }

    private fun onChangedHomeViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {
            is HomeViewModel.HomeViewState.GetSSGItemList -> {
                ssgAdapter.addAll(viewState.list)
            }

            is HomeViewModel.HomeViewState.Error -> {

            }
        }
    }
}