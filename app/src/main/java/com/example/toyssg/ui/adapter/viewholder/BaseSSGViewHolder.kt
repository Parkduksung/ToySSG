package com.example.toyssg.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSSGViewHolder<T>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

    protected lateinit var binding: ViewDataBinding

    abstract fun bind(item: T)

}