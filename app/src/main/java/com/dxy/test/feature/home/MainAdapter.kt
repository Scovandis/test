package com.dxy.test.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dxy.test.R
import com.dxy.test.data.locale.MapModels
import com.dxy.test.databinding.RowItemBinding

class MainAdapter : PagingDataAdapter<MapModels, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MapModels>() {
      override fun areItemsTheSame(oldItem: MapModels, newItem: MapModels): Boolean =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: MapModels, newItem: MapModels): Boolean =
        oldItem == newItem
    }
  }

  inner class MainViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(data: MapModels) {
      with(binding) {
        tvJudul.text = "${R.string.Omage} - ${data.name}"
        if (data.checked_visibility) {
          tvActive.visibility =  View.VISIBLE
          tvInactive.visibility = View.GONE
          tvActive.text = binding.root.context.getString(R.string.online_booking_enable)
        }else{
          tvActive.visibility =  View.VISIBLE
          tvInactive.visibility = View.GONE
          tvInactive.text = binding.root.context.getString(R.string.inactive)
        }
      }
    }
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(
      RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    val item = getItem(position)

    holder.bind(item!!)
  }
}
