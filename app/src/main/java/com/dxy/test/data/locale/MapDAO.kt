package com.dxy.test.data.locale

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MapDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addMap(favoriteMovie: MapModels)

  @Query("SELECT * FROM map_models")
  fun getAllMapData(): LiveData<List<MapModels>>

  @Query("SELECT count(*) FROM map_models WHERE map_models.name = :name")
  suspend fun checkMapName(name: String): Int

  @Query("SELECT * FROM map_models WHERE map_models.check_visibility = :checkVisibility")
  fun getAllStatusVisible(checkVisibility: Boolean): LiveData<List<MapModels>>

  @Query("DELETE FROM map_models WHERE map_models.name = :name" )
  suspend fun removeFromModels(name: String) : Int


  @Query("SELECT * FROM map_models ORDER BY model_id DESC")
  fun getPagingSource(): PagingSource<Int, MapModels>
}