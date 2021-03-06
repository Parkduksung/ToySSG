package com.example.toyssg.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.toyssg.R
import com.google.android.material.appbar.MaterialToolbar

class ToolbarHelper(activity: AppCompatActivity, private val rootView: ViewGroup) :
    ToolbarProvider {
    private var toolbar: MaterialToolbar
    private var actionBar: ActionBar?

    init {
        val toolbar = LayoutInflater.from(activity)
            .inflate(R.layout.view_toolbar, rootView, false) as MaterialToolbar

        this.toolbar = toolbar
        activity.setSupportActionBar(toolbar)
        actionBar = activity.supportActionBar

        rootView.addView(toolbar)
    }

    override fun setToolbarTitle(@StringRes titleResId: Int) {
        actionBar?.setTitle(titleResId)
    }

    override fun setToolbarTitle(title: String?) {
        actionBar?.title = title
    }

    override fun setNavigationIcon(@DrawableRes iconResId: Int) {
        actionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(iconResId)
        }
    }

    override fun setCustomView(view: View) {
        setCustomView(view, false)
    }

    override fun setCustomView(view: View, relativeHeight: Boolean) {
        toolbar.run {
            view.tag = CUSTOM_VIEW_TAG
            addView(view)
            setContentInsetsAbsolute(0, 0)
            if (relativeHeight) {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

    override fun getCustomView(): View? {
        return toolbar.findViewWithTag(CUSTOM_VIEW_TAG)
    }

    override fun setToolbarVisibility(isVisible: Boolean) {
        rootView.isVisible = isVisible
    }

    override fun setNavigationVisibility(isVisible: Boolean) {
        actionBar?.run {
            setDisplayHomeAsUpEnabled(false)
        }
    }

    companion object {
        private const val CUSTOM_VIEW_TAG = "tag"
    }
}