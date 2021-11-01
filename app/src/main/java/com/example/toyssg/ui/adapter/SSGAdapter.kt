package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGData
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.constant.SSGDataTYpe
import com.example.toyssg.ext.transViewTypeToInt
import com.example.toyssg.ui.adapter.viewholder.*


class SSGAdapter : RecyclerView.Adapter<BaseSSGViewHolder<*>>() {

    private val ssgItemList = mutableListOf<SSGData>()

    private lateinit var itemClickListener: (item: Any) -> Unit

    private val currentPreviewItemList = mutableListOf<SSGItem>()

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

            is CurrentPreviewViewHolder -> {
                holder.bind(currentPreviewItemList)
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

    fun addCurrentList(itemList: List<SSGItem>) {
        currentPreviewItemList.clear()
        if (itemList.isNotEmpty()) {
            currentPreviewItemList.addAll(itemList.reversed())
            ssgItemList.removeAll(ssgItemList.filter { it.viewType == "current_preview" })
            ssgItemList.add(1, SSGData(viewType = "current_preview", item = SSGItem()))
        } else {
            ssgItemList.removeAll(ssgItemList.filter { it.viewType == "current_preview" })
        }
        notifyDataSetChanged()
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
                SSGDataTYpe.TYPE_CURRENT_PREVIEW.ordinal -> {
                    CurrentPreviewViewHolder(parent, R.layout.item_current_preview)
                }
                else -> {
                    ProductItemViewHolder(parent, R.layout.item_product, onItemClick)
                }
            }
        }
    }
}