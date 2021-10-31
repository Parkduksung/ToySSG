package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.api.response.SSGItem

import com.example.toyssg.ui.adapter.viewholder.BaseSSGViewHolder
import com.example.toyssg.ui.adapter.viewholder.ImageViewHolder
import com.example.toyssg.ui.adapter.viewholder.ProductItemViewHolder


class SSGAdapter : RecyclerView.Adapter<BaseSSGViewHolder<SSGItem>>() {

    private val ssgItemList = mutableListOf<SSGData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSSGViewHolder<SSGItem> =
        sortViewHolder(parent, viewType)


    override fun onBindViewHolder(
        holder: BaseSSGViewHolder<SSGItem>,
        position: Int
    ) {

        holder.bind(ssgItemList[position].item)
    }

    override fun getItemCount(): Int =
        ssgItemList.size


    fun addAll(list: List<SSGData>) {
        ssgItemList.addAll(list)
        notifyDataSetChanged()
    }

    companion object {

        fun sortViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseSSGViewHolder<SSGItem> {
            return when (viewType) {

                0 -> {
                    ImageViewHolder(parent, R.layout.item_image)
                }
                1 -> {
                    ProductItemViewHolder(parent, R.layout.item_product)
                }
                else -> {
                    throw  IllegalArgumentException()
                }
            }
        }
    }
}