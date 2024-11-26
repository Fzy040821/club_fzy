package com.bw.kf.club_fengzy.profile

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bw.kf.club_fengzy.DEFAULT_PAGE_SIZE
import com.bw.kf.club_fengzy.INITIAL_LOAD_SIZE
import com.bw.kf.club_fengzy.model.VersionIntroModel
import com.bw.kf.club_fengzy.net.ApiService

import com.jczy.lib_net.exception.ApiException
import com.jczy.lib_net.exception.ResponseEmptyException

/**
 *@author Wcj
 *@description
 *@date 2022/12/1 17:10
 */
class VersionPageSource (val apiService: ApiService): PagingSource<Int, VersionIntroModel>() {


    override fun getRefreshKey(state: PagingState<Int, VersionIntroModel>): Int? {
        return 1
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VersionIntroModel> {
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val model = apiService.getAppVersionIntroByPage(currentPage, pageSize)

            if (model.data == null) {
                return LoadResult.Error(ApiException.getApiException(model.code, model.msg))
            }
            val prevKey: Int? = null
            val nextKey: Int? = if (currentPage == 1) {
                if (model.data!!.pages <= 1) {
                    null
                } else {
                    INITIAL_LOAD_SIZE / DEFAULT_PAGE_SIZE + 1
                }
            } else {
                if (model.data!!.pages <= model.data!!.current) null else currentPage + 1
            }
            if (!model.data?.items.isNullOrEmpty()) {
                LoadResult.Page(
                    data = model.data!!.items,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                //没有数据的情况
                LoadResult.Error(ResponseEmptyException())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(ApiException.getApiException(e))
        }
    }

}