package com.dxy.test.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dxy.test.data.locale.MapModels
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface PagingRepo {
  fun getPagingMapLiveData() : LiveData<PagingData<MapModels>>
  fun getPagingMapFlow(): Flow<PagingData<MapModels>>
}


class MapRepositoryImpl(private val localeDataSource: MapLocalDataSource) : PagingRepo{
  override fun getPagingMapLiveData(): LiveData<PagingData<MapModels>> =localeDataSource.getMapLiveData()

  override fun getPagingMapFlow(): Flow<PagingData<MapModels>> =localeDataSource.getMapFlow()

}