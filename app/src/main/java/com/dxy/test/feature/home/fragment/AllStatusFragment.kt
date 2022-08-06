package com.dxy.test.feature.home.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.test.data.locale.MapModels
import com.dxy.test.databinding.FragmentStatusBinding
import com.dxy.test.feature.home.MainAdapter
import com.dxy.test.feature.home.MainLoadStateAdapter
import com.dxy.test.feature.home.MainViewModels
import com.dxy.test.feature.home.adapter.MainMapAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class AllStatusFragment : Fragment(){
  private val TAG = AllStatusFragment::class.java.simpleName

  lateinit var mainAdapter: MainMapAdapter


  private val viewModels  by viewModels<MainViewModels>()
  lateinit var binding: FragmentStatusBinding
  lateinit var mAdapter: MainAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentStatusBinding.inflate(inflater, container, false)

//    setData()
//    initRecyclerview()
//    initRecyclerView()
//    initViewModel()
//    val adapter = MainAdapter()
    mAdapter = MainAdapter()

    binding.rvContent.adapter = mAdapter.withLoadStateFooter(
      MainLoadStateAdapter()
    )

    lifecycleScope.launch {
      viewModels.data.collectLatest {
        mAdapter.submitData(it)
      }
    }

    viewModels.insertMap()
    initRecyclerView()
    return binding.root
  }

  private fun initRecyclerView() {
    binding.rvContent.apply {
      layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(decoration)
      adapter = mAdapter
    }
  }

  private fun initViewModel() {
    lifecycleScope.launchWhenCreated {
      viewModels.getPagingSource().collectLatest {
        Log.d(TAG, "DATAA PAGING ${it}")
        mAdapter.submitData(it)
      }
    }
//    viewModels.insertMap()
  }
  private fun setData(){
    viewModels.listLaporan.observe(viewLifecycleOwner, Observer {
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
      adapter = mainAdapter
    }
  }

  override fun onPause() {
    binding.shimmer1.stopShimmer()
    super.onPause()
  }

  override fun onResume() {
    super.onResume()
    binding.shimmer1.startShimmer()
    lifecycleScope.launch {
      viewModels.data.collectLatest {
        mAdapter.submitData(it)
      }
    }

  }
}