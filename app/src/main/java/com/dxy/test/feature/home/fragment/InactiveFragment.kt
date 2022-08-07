package com.dxy.test.feature.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.test.data.locale.MapModels
import com.dxy.test.databinding.FragmentStatusBinding
import com.dxy.test.feature.home.MainAdapter
import com.dxy.test.feature.home.MainViewModels
import com.dxy.test.feature.home.adapter.MainMapAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InactiveFragment : Fragment(){
  private val mainAdapter: MainMapAdapter by lazy{MainMapAdapter()}
  private val viewModels  by viewModels<MainViewModels>()
  lateinit var binding: FragmentStatusBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentStatusBinding.inflate(inflater, container, false)

    setData()
//    initViewModels()
    initRecyclerview()

    return binding.root
  }
  private fun initViewModels(){
    lifecycleScope.launch {
      viewModels.getDataWhere(status = false).observe(viewLifecycleOwner){
//        mainAdapter.submitData(lifecycle, it)
      }
    }

  }
  private fun setData(){
    viewModels.getDataWhereStatus(false).observe(viewLifecycleOwner, Observer {
      binding.shimmer1.stopShimmer()
      binding.shimmer1.visibility = View.GONE
      if (it.isEmpty()){
        binding.lottieAnimation.visibility = View.VISIBLE
        binding.tvIsEmpty.visibility = View.VISIBLE
        binding.rvContent.visibility = View.GONE
      }else{
        binding.lottieAnimation.visibility = View.GONE
        binding.tvIsEmpty.visibility = View.GONE
        binding.rvContent.visibility = View.VISIBLE

        mainAdapter.setAddList(it as MutableList<MapModels>)
      }
    })
  }
  private fun initRecyclerview(){
    binding.rvContent.apply {
      layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(decoration)
      adapter = mainAdapter
    }
  }

  override fun onPause() {
    binding.shimmer1.stopShimmer()
    super.onPause()
  }

  override fun onResume() {
    super.onResume()
    binding.shimmer1.stopShimmer()
  }
}