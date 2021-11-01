package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.BR
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.databinding.ItemCurrentPreviewBinding
import com.example.toyssg.databinding.ItemProductPreviewBinding
import com.example.toyssg.ui.adapter.viewholder.BaseSSGViewHolder

class CurrentPreviewAdapter : RecyclerView.Adapter<BaseSSGViewHolder<*>>() {

    private val currentViewSet = mutableSetOf<SSGItem>()
    private val currentViewList get() = currentViewSet.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSSGViewHolder<*> =
        PreviewProductViewHolder(parent, R.layout.item_product_preview)

    override fun onBindViewHolder(holder: BaseSSGViewHolder<*>, position: Int) {
        (holder as PreviewProductViewHolder).bind(currentViewList[position])
    }

    override fun getItemCount(): Int =
        currentViewList.size

    fun addAll(list: List<SSGItem>) {
        currentViewSet.addAll(list)
        notifyDataSetChanged()
    }

}

class PreviewProductViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) :
    BaseSSGViewHolder<SSGItem>(parent, layoutId) {

    init {
        binding = ItemProductPreviewBinding.bind(itemView)
    }

    override fun bind(item: SSGItem) {
        binding.run {
            setVariable(BR.ssgItem, item)
            executePendingBindings()
        }
    }
}

class CurrentPreviewViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) :
    BaseSSGViewHolder<List<SSGItem>>(parent, layoutId) {

    private val currentPreviewAdapter = CurrentPreviewAdapter()

    init {
        binding = ItemCurrentPreviewBinding.bind(itemView)
    }

    override fun bind(item: List<SSGItem>) {

        (binding as ItemCurrentPreviewBinding).rvCurrentHorizion.run {
            adapter = currentPreviewAdapter
            setHasFixedSize(true)
            currentPreviewAdapter.addAll(item)
        }
    }
}