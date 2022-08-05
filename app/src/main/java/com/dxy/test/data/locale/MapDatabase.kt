package com.dxy.test.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
  entities = [MapModels::class],
  version = 1
)
abstract class MapDatabase : RoomDatabase(){
  abstract fun getMapDao() : MapDAO
}