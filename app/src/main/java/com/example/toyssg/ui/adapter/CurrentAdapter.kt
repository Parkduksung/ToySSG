package com.example.toyssg.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyssg.R
import com.example.toyssg.api.response.SSGItem
import com.example.toyssg.ui.adapter.viewholder.BaseSSGViewHolder
import com.example.toyssg.ui.adapter.viewholder.CurrentItemViewHolder

class CurrentAdapter : RecyclerView.Adapter<BaseSSGViewHolder<*>>() {

    private val currentViewSet = mutableSetOf<SSGItem>()
    private val currentViewList get() = currentViewSet.toList().reversed()

    private lateinit var itemClickListener: (item: Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSSGViewHolder<*> =
        CurrentItemViewHolder(parent, R.layout.item_current, itemClickListener)

    override fun onBindViewHolder(holder: BaseSSGViewHolder<*>, position: Int) {
        (holder as CurrentItemViewHolder).bind(currentViewList[position])
    }

    override fun getItemCount(): Int =
        currentViewList.size


    fun addAll(list: List<SSGItem>) {
        currentViewSet.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (item: Any) -> Unit) {
        itemClickListener = listener
    }

    fun removeItem(item: SSGItem) {
        currentViewSet.remove(item)
        notifyDataSetChanged()
    }


}