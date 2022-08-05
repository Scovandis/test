package com.dxy.test.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dxy.test.data.MapRepository
import com.dxy.test.data.locale.MapModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModels @Inject constructor(private val repository: MapRepository): ViewModel(){

  var listLaporan = repository.getMapModels()

  fun getDataWhereStatus(status: Boolean): LiveData<List<MapModels>> {
    return repository.getMapWhereStatus(status)
  }


  fun getPagingSource(): Flow<PagingData<MapModels>> {
    return Pager(config = PagingConfig(pageSize = 20, maxSize = 100),
      pagingSourceFactory = {repository.getAllMap()}).flow.cachedIn(viewModelScope)
  }

  fun insertMap() {
    for(i in 1..100) {
      repository.insertMap(MapModels(0, "name", "addres", "city", "zipcode", false))
    }
  }
}
