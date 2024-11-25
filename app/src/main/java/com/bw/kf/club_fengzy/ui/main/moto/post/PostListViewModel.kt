package com.bw.kf.club_fengzy.ui.main.moto.post

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.bw.kf.club_fengzy.base.BaseViewModel
import com.bw.kf.club_fengzy.model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val mRepository: MotoCircleRepository
): BaseViewModel() {

    private var category: Int? = null
    private var vehicleModelId: String? = null
    private var series: String? = null
    private var keyword: String? = null
    private var memberId: String? = null
    private var ifMy: Int? = null
    private var ifFocus: Int? = null
    private var ifCollection: Boolean? = null
    private var module: Int = 1
    private val _removePostItemFlow = MutableStateFlow(mutableListOf<PostModel>())
    private val removePostItemFlow: Flow<MutableList<PostModel>> get() = _removePostItemFlow
    //设置帖子分页参数
    fun setPageParam(
        category: Int?,
        vehicleModelId: String?,
        series: String?,
        keyword: String?,
        memberId: String?,
        module: Int = 1,
        ifMy: Int?,
        ifFocus: Int?,
        ifCollection: Boolean?
    ) {
        this.category = category
        this.vehicleModelId = vehicleModelId
        this.series = series
        this.keyword = keyword
        this.memberId = memberId
        this.ifMy = ifMy
        this.ifFocus = ifFocus
        this.ifCollection = ifCollection
        this.module = module
    }


    val postPageEvent2
        get() = mRepository.queryPostPage(
            category,
            vehicleModelId,
            series,
            keyword,
            memberId,
            ifMy,
            ifFocus,
            ifCollection,
            module
        )
            .cachedIn(viewModelScope)
            .combine(removePostItemFlow) { data, removed ->
                data.filter {
                    !removed.any { remove -> remove.postsId == it.postsId }
                }
            }
            .shareIn(viewModelScope, SharingStarted.Lazily)

}