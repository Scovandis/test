package com.dxy.test.di.locale

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dxy.test.data.locale.MapDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocaleModules {
  private val migration_1_2: Migration = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
      database.execSQL("ALTER TABLE userinfo ADD COLUMN phone ''")
    }
  }

  @Singleton
  @Provides
  fun provideMapModelsDatabase(
    @ApplicationContext app: Context
  )= Room.databaseBuilder(
    app,
    MapDatabase::class.java,
    "map_models")
    .addMigrations(migration_1_2)
    .allowMainThreadQueries()
    .build()


  @Singleton
  @Provides
  fun provideMapDAO(db: MapDatabase) = db.getMapDao()


}