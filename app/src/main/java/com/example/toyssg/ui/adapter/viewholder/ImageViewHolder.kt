package com.example.toyssg.ui.adapter.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.toyssg.BR
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.databinding.ItemImageBinding

class ImageViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseSSGViewHolder<SSGItem>(parent, layoutId) {

    init {
        binding = ItemImageBinding.bind(itemView)
    }

    override fun bind(item: SSGItem) {

        binding.run {
            setVariable(BR.ssgItem, item)
            executePendingBindings()
        }
    }
}

