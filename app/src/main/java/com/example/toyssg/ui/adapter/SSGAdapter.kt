package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.constant.SSGDataTYpe
import com.example.toyssg.ext.transViewTypeToInt
import com.example.toyssg.ui.adapter.viewholder.BaseSSGViewHolder
import com.example.toyssg.ui.adapter.viewholder.ImageViewHolder
import com.example.toyssg.ui.adapter.viewholder.ProductItemViewHolder


class SSGAdapter : RecyclerView.Adapter<BaseSSGViewHolder<*>>() {

    private val ssgItemList = mutableListOf<SSGData>()

    private lateinit var itemClickListener: (item: Any) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSSGViewHolder<*> =
        sortViewHolder(parent, viewType, itemClickListener)


    override fun getItemViewType(position: Int): Int =
        ssgItemList[position].transViewTypeToInt()


    override fun onBindViewHolder(
        holder: BaseSSGViewHolder<*>,
        position: Int
    ) {
        when (holder) {
            is ImageViewHolder -> {
                holder.bind(ssgItemList[position].item)
            }
            is ProductItemViewHolder -> {
                holder.bind(ssgItemList[position].item)
            }
        }
    }

    override fun getItemCount(): Int =
        ssgItemList.size


    fun addAll(list: List<SSGData>) {
        ssgItemList.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (item: Any) -> Unit) {
        itemClickListener = listener
    }

    companion object {

        fun sortViewHolder(
            parent: ViewGroup,
            viewType: Int,
            onItemClick: (item: Any) -> Unit
        ): BaseSSGViewHolder<*> {
            return when (viewType) {
                SSGDataTYpe.TYPE_IMAGE.ordinal -> {
                    ImageViewHolder(parent, R.layout.item_image)
                }
                else -> {
                    ProductItemViewHolder(parent, R.layout.item_product, onItemClick)
                }
            }
        }
    }
}