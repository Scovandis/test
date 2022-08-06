package com.dxy.test.feature.home

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dxy.test.data.locale.MapDAO
import com.dxy.test.data.locale.MapModels
import kotlinx.coroutines.delay
import javax.inject.Inject


class MainPagingSource @Inject constructor(
  private val dao: MapDAO
) : PagingSource<Int, MapModels>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MapModels> {
    val page = params.key ?: 0

    return try {
      val entities = dao.getPagedList(params.loadSize, page * params.loadSize)

      // simulate page loading
      if (page != 0) delay(1000)

      LoadResult.Page(
        data = entities,
        prevKey = if (page == 0) null else page - 1,
        nextKey = if (entities.isEmpty()) null else page + 1
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, MapModels>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}