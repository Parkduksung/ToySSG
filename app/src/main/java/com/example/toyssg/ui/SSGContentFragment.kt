package com.example.toyssg.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.base.BaseFragment
import com.example.toyssg.databinding.ContentFrgBinding
import com.example.toyssg.ui.adapter.SSGAdapter
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SSGContentFragment : BaseFragment<ContentFrgBinding>(R.layout.content_frg) {

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private val ssgAdapter by lazy { SSGAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(EMPTY_TITLE)
        setNavigationVisibility(false)
        setToolbarVisibility(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_content, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.current -> {
                homeViewModel.routeCurrent()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUi() {
        binding.rvSsg.apply {
            adapter = ssgAdapter
            setHasFixedSize(true)
        }

        ssgAdapter.setOnItemClickListener { item ->
            when (item) {
                is SSGItem -> {
                    homeViewModel.routeDetail(item)
                }
            }
        }
    }

    private fun initViewModel() {
        binding.viewModel = homeViewModel

        homeViewModel.viewStateLiveData.observe(requireActivity()) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedViewState(viewState) }
        }

        homeViewModel.getSSGItemResponse()
    }


    private fun onChangedViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {
            is HomeViewModel.HomeViewState.GetSSGItemList -> {
                ssgAdapter.addAll(viewState.list)
            }

            is HomeViewModel.HomeViewState.GetCurrentItemList -> {

            }

            is HomeViewModel.HomeViewState.EmptyCurrentItem -> {

            }
        }
    }

    companion object {

        private const val EMPTY_TITLE = ""

    }
}
