package com.example.toyssg.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.toyssg.R
import com.example.toyssg.base.BaseFragment
import com.example.toyssg.databinding.CurrentFrgBinding
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SSGCurrentFragment : BaseFragment<CurrentFrgBinding>(R.layout.current_frg) {

    private val homeViewModel by activityViewModels<HomeViewModel>()


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

    }

    private fun initViewModel() {

        homeViewModel.viewStateLiveData.observe(requireActivity()) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedViewState(viewState) }
        }
    }


    private fun onChangedViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {

        }
    }
}
