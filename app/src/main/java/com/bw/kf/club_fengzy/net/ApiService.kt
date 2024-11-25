package com.bw.kf.club_fengzy.net

import com.bw.kf.club_fengzy.base.BaseModel
import com.bw.kf.club_fengzy.model.PostPageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("blank")
    suspend fun blankApi(): BaseModel<Any>

    /**
     * 分页获取帖子
     * @param category 帖子归属(0:动态 1:推荐 2:热门)
     */
    @GET(API.POST_PAGE)
    suspend fun queryPostsByPage(
        @Query("category") category: Int? = null,
        @Query("topicId") topicId: String? = null,
        @Query("vehicleModelId") vehicleModelId: String? = null,
        @Query("series") series: String? = null,
        @Query("content") content: String? = null,
        @Query("memberId") memberId: String? = null,
        @Query("ifMy") ifMy: Int? = null,
        @Query("ifFocus") ifFocus: Int? = null,
        @Query("topicCategory") topicCategory: Int? = null,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("isAsc") isAsc: Boolean? = null,
        @Query("orderByColumn") orderByColumn: String? = null,
    ): BaseModel<PostPageModel>

}