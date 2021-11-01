package com.example.toyssg.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.toyssg.R
import com.example.toyssg.base.BaseActivity
import com.example.toyssg.databinding.HomeActBinding
import com.example.toyssg.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeActBinding>(R.layout.home_act) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        homeViewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedHomeViewState(viewState) }
        }
    }

    private fun onChangedHomeViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {
            is HomeViewModel.HomeViewState.RouteContent -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_route, SSGContentFragment()).commit()
            }

            is HomeViewModel.HomeViewState.RouteDetail -> {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container_route,
                        SSGDetailFragment.newInstance(viewState.item)
                    ).commit()
            }

            is HomeViewModel.HomeViewState.RouteCurrent -> {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(
                        R.id.container_route,
                        SSGCurrentFragment()
                    ).commit()
            }

            is HomeViewModel.HomeViewState.ShowProgress -> {
                with(binding.progressbar) {
                    bringToFront()
                    isVisible = true
                }
            }

            is HomeViewModel.HomeViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }

            is HomeViewModel.HomeViewState.Error -> {
                Toast.makeText(this, viewState.message, Toast.LENGTH_SHORT).show()
            }

            is HomeViewModel.HomeViewState.DeleteCurrentItem -> {
                homeViewModel.isExistCurrentItemList()
            }
        }
    }
}