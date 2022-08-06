package com.dxy.test.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dxy.test.data.MapRepository
import com.dxy.test.data.PagingRepo
import com.dxy.test.data.locale.MapDAO
import com.dxy.test.data.locale.MapModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModels @Inject constructor(private val repository: MapRepository, private val dao: MapDAO): ViewModel(){

  var listLaporan = repository.getMapModels()
//  val data1 : LiveData<PagedList<MapModels>>
  val getFlow = repository.getPagingMapFlow()
  val getLiveData = repository.getPagingMapLiveData()


  val data = Pager(
    PagingConfig(
      pageSize = 20,
      enablePlaceholders = false,
      initialLoadSize = 20
    ),
  ) {
    MainPagingSource(dao)
  }.flow.cachedIn(viewModelScope)

  fun getDataWhereStatus(status: Boolean): LiveData<List<MapModels>> {
    return repository.getMapWhereStatus(status)
  }


  fun getPagingSource(): Flow<PagingData<MapModels>> {
    return Pager(config = PagingConfig(pageSize = 20, maxSize = 100),
      pagingSourceFactory = {repository.getAllMap()}).flow.cachedIn(viewModelScope)
  }

  fun insertMap() {
    for(i in 1..500) {
      if (i % 2 == 0){
        repository.insertMap(MapModels(i, "name ${i}", "addres", "city", "zipcode", false))
      }else{
        repository.insertMap(MapModels(i, "name ${i}", "addres", "city", "zipcode", true))
      }

    }
  }

  fun getPageLimitOffset(limit: Int, offset: Int):LiveData<List<MapModels>>{
    return repository.getPagedOffset(limit, offset)
  }
}
