package com.bw.kf.club_fengzy.ui.main.moto.post


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bw.kf.club_fengzy.DEFAULT_PAGE_SIZE
import com.bw.kf.club_fengzy.INITIAL_LOAD_SIZE
import com.bw.kf.club_fengzy.model.PostModel
import com.bw.kf.club_fengzy.net.ApiService
import com.jczy.lib_net.exception.ApiException
import com.jczy.lib_net.exception.ResponseEmptyException

class PostPageSource(
    private val category: Int? = null,
    private val topicId: String? = null,
    private val keyword: String? = null,
    private val vehicleModelId: String? = null,
    private val series: String? = null,
    private val memberId: String? = null,
    private val ifMy: Int? = null,
    private val ifFocus: Int? = null,
    private val ifCollection: Boolean? = null,
    private val topicCategory: Int? = null,
    private val apiService: ApiService,
    //总条数
    private val totalSizeListener: ((Int) -> Unit)? = null,
    private val module: Int = 1
) : PagingSource<Int, PostModel>() {
    override fun getRefreshKey(state: PagingState<Int, PostModel>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModel> {
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val posts =  apiService.queryPostsByPage(
                category = category,
                topicId = topicId,
                vehicleModelId = vehicleModelId,
                series = series,
                content = keyword,
                memberId = memberId,
                ifMy = ifMy,
                ifFocus = ifFocus,
                topicCategory = topicCategory,
                currentPage,
                pageSize
            )
            val prevKey: Int? = null
            val nextKey: Int? = if (currentPage == 1) {
                if (posts.data!!.pages <= 1) {
                    null
                } else {
                    INITIAL_LOAD_SIZE / DEFAULT_PAGE_SIZE + 1
                }
            } else {
                if (posts.data!!.pages <= posts.data!!.current) null else currentPage + 1
            }
            if (!posts.data?.posts.isNullOrEmpty()) {
                LoadResult.Page(
                    data = posts.data!!.posts,
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