package com.example.toyssg.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.base.BaseFragment
import com.example.toyssg.databinding.CurrentFrgBinding
import com.example.toyssg.ui.adapter.CurrentAdapter
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SSGCurrentFragment : BaseFragment<CurrentFrgBinding>(R.layout.current_frg) {

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private val currentAdapter by lazy { CurrentAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        setNavigationIcon(R.drawable.ic_back)
        setToolbarTitle(getString(R.string.current_view))
        setToolbarVisibility(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                homeViewModel.routeContent()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUi() {
        binding.rvCurrent.run {
            adapter = currentAdapter
        }

        currentAdapter.setOnItemClickListener { item ->
            homeViewModel.deleteCurrentSSGItem((item as SSGItem))
        }
    }

    private fun initViewModel() {

        homeViewModel.viewStateLiveData.observe(requireActivity()) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedViewState(viewState) }
        }

        homeViewModel.getCurrentItemList()
    }

    private fun onChangedViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {
            is HomeViewModel.HomeViewState.GetCurrentItemList -> {
                toggleViewChange(isHasCurrentItem = true)
                currentAdapter.addAll(viewState.list)
            }

            is HomeViewModel.HomeViewState.DeleteCurrentItem -> {
                currentAdapter.removeItem(viewState.item)
            }

            is HomeViewModel.HomeViewState.EmptyCurrentItem -> {
                toggleViewChange(isHasCurrentItem = false)
                binding.tvEmptyContent.bringToFront()
            }
        }
    }

    private fun toggleViewChange(isHasCurrentItem: Boolean) {

        with(binding) {
            rvCurrent.isVisible = isHasCurrentItem
            tvEmptyContent.isVisible = !isHasCurrentItem

            if (!isHasCurrentItem) {
                tvEmptyContent.bringToFront()
            }
        }
    }
}
