package com.example.toyssg.ui.adapter.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.toyssg.BR
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.databinding.ItemProductBinding


class ProductItemViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    private val onItemClick: ((item: Any) -> Unit)? = null
) :
    BaseSSGViewHolder<SSGItem>(parent, layoutId, onItemClick) {

    init {
        binding = ItemProductBinding.bind(itemView)
    }

    override fun bind(item: SSGItem) {
        binding.run {
            setVariable(BR.ssgItem, item)
            executePendingBindings()
        }

        itemView.setOnClickListener {
            onItemClick?.let { it(item) }
        }
    }
}

