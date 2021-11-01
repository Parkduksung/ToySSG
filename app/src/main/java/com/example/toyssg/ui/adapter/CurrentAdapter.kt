package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.ui.adapter.viewholder.BaseSSGViewHolder
import com.example.toyssg.ui.adapter.viewholder.ImageViewHolder

class CurrentAdapter : RecyclerView.Adapter<BaseSSGViewHolder<*>>() {

    private val currentViewSet = mutableSetOf<SSGItem>()
    private val currentViewList get() = currentViewSet.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSSGViewHolder<*> =
        ImageViewHolder(parent, R.layout.item_image)

    override fun onBindViewHolder(holder: BaseSSGViewHolder<*>, position: Int) {
        (holder as ImageViewHolder).bind(currentViewList[position])
    }

    override fun getItemCount(): Int =
        currentViewList.size


    fun addAll(list: List<SSGItem>) {
        currentViewSet.addAll(list)
        notifyDataSetChanged()
    }


}