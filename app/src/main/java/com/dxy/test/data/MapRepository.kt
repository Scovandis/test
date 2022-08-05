package com.dxy.test.data

import androidx.paging.PagingSource
import com.dxy.test.data.locale.MapDAO
import com.dxy.test.data.locale.MapModels
import javax.inject.Inject

class MapRepository @Inject constructor(private val mapDAO: MapDAO){
  suspend fun addToModels(mapModels: MapModels) = mapDAO.addMap(mapModels)
  fun getMapModels() =mapDAO.getAllMapData()
  fun getMapWhereStatus(status: Boolean) = mapDAO.getAllStatusVisible(status)

  fun getAllMap(): PagingSource<Int, MapModels> {
    return mapDAO.getPagingSource()
  }

  fun insertMap(characterData: MapModels) {
    mapDAO.addMap(characterData)
  }

  suspend fun checkMapByName(name: String) = mapDAO.checkMapName(name)
  suspend fun removeMapModels(name: String) {
    mapDAO.removeFromModels(name)
  }
}