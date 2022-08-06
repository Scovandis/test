package com.dxy.test.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dxy.test.data.locale.MapDAO
import javax.inject.Inject


class MapLocalDataSource @Inject constructor(private val mapDAO: MapDAO){
  fun getMapFlow() = Pager(
    config = PagingConfig(
      pageSize = 20,
      maxSize = 500
    )
  )
  { mapDAO.getPagingSource() }
    .flow

  fun getMapLiveData() = Pager(
    config = PagingConfig(
      pageSize = 20,
      maxSize = 500
    )
  )
  { mapDAO.getPagingSource() }
    .liveData
}