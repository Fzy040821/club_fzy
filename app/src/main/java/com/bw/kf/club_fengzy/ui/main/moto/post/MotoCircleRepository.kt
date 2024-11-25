package com.bw.kf.club_fengzy.ui.main.moto.post

import androidx.paging.Pager
import com.bw.kf.club_fengzy.globalPagingConfig
import com.bw.kf.club_fengzy.net.ApiService
import javax.inject.Inject

class MotoCircleRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    fun queryPostPage(
        category: Int?,
        vehicleModelId: String?,
        series: String?,
        keyword: String?,
        memberId: String?,
        ifMy: Int?,
        ifFocus: Int?,
        ifCollection: Boolean?,
        module: Int
    ) = Pager(
        config = globalPagingConfig,
        pagingSourceFactory = {
            PostPageSource(
                category = category,
                vehicleModelId = vehicleModelId,
                series = series,
                keyword = keyword,
                memberId = memberId,
                ifMy = ifMy,
                ifFocus = ifFocus,
                ifCollection = ifCollection,
                module = module,
                apiService = apiService
            )
        })
        .flow

}