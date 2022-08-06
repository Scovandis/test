package com.dxy.test.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dxy.test.data.locale.MapDAO
import com.dxy.test.data.locale.MapModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepository @Inject constructor(private val mapDAO: MapDAO,private val localeDataSource: MapLocalDataSource): PagingRepo{
  suspend fun addToModels(mapModels: MapModels) = mapDAO.addMap(mapModels)
  fun getMapModels() =mapDAO.getAllMapData()
  fun getMapWhereStatus(status: Boolean) = mapDAO.getAllStatusVisible(status)
  fun getAllMap(): PagingSource<Int, MapModels> {
    return mapDAO.getPagingSource()
  }
  fun getPagedOffset(limit: Int, offset: Int) = mapDAO.getPagedListES(limit, offset)

  fun insertMap(mapData: MapModels) {
    mapDAO.addMap(mapData)
  }


  suspend fun checkMapByName(name: String) = mapDAO.checkMapName(name)
  suspend fun removeMapModels(name: String) {
    mapDAO.removeFromModels(name)
  }

  override fun getPagingMapLiveData(): LiveData<PagingData<MapModels>> =localeDataSource.getMapLiveData()

  override fun getPagingMapFlow(): Flow<PagingData<MapModels>> =localeDataSource.getMapFlow()

}