package com.example.toyssg.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    protected lateinit var binding: B

    protected val baseActivity: BaseActivity<*>?
        get() = activity as BaseActivity<*>?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun setToolbarVisibility(isVisible: Boolean) {
        baseActivity?.setToolbarVisibility(isVisible)
    }

    protected fun setNavigationIcon(@DrawableRes iconResId: Int) {
        baseActivity?.setNavigationIcon(iconResId)
    }

    protected fun setToolbarTitle(title: String?) {
        baseActivity?.setToolbarTitle(title)
    }
    protected fun setToolbarTitle(@StringRes titleResId: Int) {
        baseActivity?.setToolbarTitle(titleResId)
    }

    protected fun setNavigationVisibility(isVisible: Boolean) {
        baseActivity?.setNavigationVisibility(isVisible)
    }
}