package com.example.toyssg.ui.adapter.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.databinding.ItemCurrentBinding
import com.example.toyssg.ui.adapter.CurrentAdapter

class CurrentItemViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseSSGViewHolder<List<SSGItem>>(parent, layoutId) {

    init {
        binding = ItemCurrentBinding.bind(itemView)
    }

    private val currentAdapter = CurrentAdapter()

    override fun bind(item: List<SSGItem>) {

        (binding as? ItemCurrentBinding)?.rvCurrent?.run {
            adapter = currentAdapter
            setHasFixedSize(true)
            currentAdapter.addAll(
                item
            )
        }
    }
}