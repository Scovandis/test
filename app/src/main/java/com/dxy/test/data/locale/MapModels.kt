package com.dxy.test.data.locale

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "map_models")
@Parcelize
data class MapModels(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "model_id")
  var id: Int = 0,
  @ColumnInfo(name = "name")
  var name: String,
  @ColumnInfo(name = "address")
  var address: String,
  @ColumnInfo(name = "city")
  var city: String,
  @ColumnInfo(name = "zip_code")
  var zip_code: String,
  @ColumnInfo(name = "check_visibility")
  var checked_visibility: Boolean
):  Parcelable {}