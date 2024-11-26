package com.bw.kf.club_fengzy.net

import com.bw.kf.club_fengzy.base.BaseModel
import com.bw.kf.club_fengzy.model.BasePageModel
import com.bw.kf.club_fengzy.model.PostPageModel
import com.bw.kf.club_fengzy.model.UpdateModel
import com.bw.kf.club_fengzy.model.UploadImageModel
import com.bw.kf.club_fengzy.model.VersionIntroModel
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @GET("blank")
    suspend fun blankApi(): BaseModel<Any>

    /**
     * APP检查更新:
     * type: 1: 安卓   2:IOS
     */
    @GET("club-system/appLevel/check")
    suspend fun appUpdate(@Query("platform") platform: String = "ANDROID"): BaseModel<UpdateModel>

    /**
     * 多文件上传
     */
    @Multipart
    @POST("club-system/file/uploadBatch")
    suspend fun upLoadFile(@Part params: List<MultipartBody.Part>): BaseModel<UploadImageModel>

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

    /**分页获取APP版本分页数据*/
    @GET(API.GET_VERSION_INTRO_BY_PAGE)
    suspend fun getAppVersionIntroByPage(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("verType") verType: String = "Android",
    ): BaseModel<BasePageModel<VersionIntroModel>>

    /**分页获取APP版本介绍*/
    @GET(API.GET_VERSION_INTRO)
    suspend fun getAppVersionIntro(
        @Query("id") id: String,
    ): BaseModel<VersionIntroModel>

}