package com.dxy.test.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dxy.test.R
import com.dxy.test.data.locale.MapModels
import com.dxy.test.databinding.RowItemBinding

class MainMapAdapter  : RecyclerView.Adapter<MainMapAdapter.ViewHolder>(){
  private var list = mutableListOf<MapModels>()

  fun setAddList(lists  : MutableList<MapModels>){
    this.list = lists
    notifyDataSetChanged()
  }

  inner class ViewHolder(private val binding: RowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MapModels) {
      with(binding) {
        tvJudul.text = "${binding.root.context.getString(R.string.Omage)} - ${data.name}"

        if (data.checked_visibility) {
          tvActive.visibility =  View.VISIBLE
          tvInactive.visibility = View.GONE
          tvActive.text = binding.root.context.getString(R.string.online_booking_enable)
        }else{
          tvActive.visibility =  View.GONE
          tvInactive.visibility = View.VISIBLE
          tvInactive.text = binding.root.context.getString(R.string.inactive)
        }
      }
    }
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if (position % 2 ==0){
      holder.itemView.rootView.setBackgroundResource(R.color.white)
    }else {
      holder.itemView.rootView.setBackgroundResource(R.color.gray)
    }
    holder.bind(list[position])
  }

  override fun getItemCount()= list.size
}