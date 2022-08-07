package com.dxy.test.data

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.dxy.test.data.locale.MapDAO
import javax.inject.Inject


class MapLocalDataSource @Inject constructor(private val mapDAO: MapDAO){
  fun getMapFlow() = Pager(
    config = PagingConfig(
      pageSize = 20,
      maxSize = 600
    )
  )
  { mapDAO.getPagingSource() }
    .flow

  fun getMapDataWhere(status: Boolean) = Pager(
    config = PagingConfig(
      pageSize = 20,
      maxSize = 600
    )
  ){ mapDAO.getPagingSourceWhereStatus(status)}.liveData
  fun getMapLiveData() = Pager(
    config = PagingConfig(
      pageSize = 20,
      maxSize = 600
    )
  )
  { mapDAO.getPagingSource() }
    .liveData
}