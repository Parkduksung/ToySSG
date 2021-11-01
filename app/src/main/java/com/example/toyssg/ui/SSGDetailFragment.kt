package com.example.toyssg.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.base.BaseFragment
import com.example.toyssg.databinding.DetailFrgBinding
import com.example.toyssg.ext.setUrlImg
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SSGDetailFragment : BaseFragment<DetailFrgBinding>(R.layout.detail_frg) {

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()

    }

    override fun onResume() {
        super.onResume()
        setNavigationIcon(R.drawable.ic_back)
        setToolbarTitle(getString(R.string.detail_view))
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
        arguments?.getParcelable<SSGItem>(KEY_ITEM)?.let {
            upDataUi(it)
        }
    }

    private fun initViewModel() {
        binding.viewModel = homeViewModel

        homeViewModel.viewStateLiveData.observe(requireActivity()) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedViewState(viewState) }
        }
    }

    private fun onChangedViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {

        }
    }

    private fun upDataUi(item: SSGItem) {
        with(binding) {
            ivImage.setUrlImg(item.image)
            tvTitle.text = item.name
            tvPrice.text = item.price
        }
    }

    companion object {

        const val KEY_ITEM = "key_item"

        fun newInstance(item: SSGItem): SSGDetailFragment =
            SSGDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_ITEM, item)
                }
            }
    }
}
