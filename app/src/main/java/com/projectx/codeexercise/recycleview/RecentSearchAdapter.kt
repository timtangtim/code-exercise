package com.projectx.codeexercise.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.projectx.codeexercise.databinding.ItemCityBinding

class RecentSearchAdapter(var recentSearch: MutableLiveData<MutableSet<String>>?) :
    RecyclerView.Adapter<DeviceListViewHolder>() {
    companion object{
        const val TAG = "RecentSearchAdapter"
    }
    var onClickListener: View.OnClickListener? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
        return DeviceListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${recentSearch?.value?.size}")
        if (recentSearch != null && recentSearch!!.value != null) {
            return recentSearch!!.value!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        recentSearch?.value?.let {
            holder.itemBinding.root.tag = position
            holder.itemBinding.selected.tag = it.toList()[position]
            holder.itemBinding.cityName.text = it.toList()[position]
            holder.itemBinding.cityName.setOnClickListener(onClickListener)
            holder.itemBinding.selected.setOnClickListener(onClickListener)
        }
    }
}

class DeviceListViewHolder(var itemBinding: ItemCityBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
}