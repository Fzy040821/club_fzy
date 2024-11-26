package com.bw.kf.club_fengzy.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.adapter.PagingFooterAdapter
import com.bw.kf.club_fengzy.base.BaseToolbarActivity
import com.bw.kf.club_fengzy.base.BaseViewModel
import com.bw.kf.club_fengzy.base.observeWithLifecycle
import com.bw.kf.club_fengzy.common.CommonWebActivity
import com.bw.kf.club_fengzy.databinding.ActivityVersionIntroBinding
import com.bw.kf.club_fengzy.databinding.LayoutRefreshRecyclerviewBinding
import com.bw.kf.club_fengzy.globalPagingConfig
import com.bw.kf.club_fengzy.model.VersionIntroModel
import com.bw.kf.club_fengzy.net.ApiService
import com.bw.kf.club_fengzy.state.Default
import com.bw.kf.club_fengzy.state.Failed
import com.bw.kf.club_fengzy.state.Loading
import com.bw.kf.club_fengzy.state.RequestEvent
import com.bw.kf.club_fengzy.state.Success
import com.bw.kf.club_fengzy.ui.base.state.UILoader
import com.bw.kf.club_fengzy.ui.base.state.UIState
import com.bw.kf.club_fengzy.util.JLogUtil.postEvent
import com.bw.kf.club_fengzy.util.ToastUtils

import com.jczy.lib_net.exception.ApiException
import com.jczy.lib_net.exception.LoginTimeoutException
import com.jczy.lib_net.exception.ResponseEmptyException
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

/**
 *@author Wcj
 *@description 版本更新介绍
 *@date 2022/12/1 16:23
 */
@AndroidEntryPoint
@Route(path = Router.Ui.VersionIntroActivity)
class VersionIntroActivity : BaseToolbarActivity<VersionIntroViewModel, ActivityVersionIntroBinding>() {
    override val mViewModel: VersionIntroViewModel by viewModels()
    override val mLayoutResId: Int
        get() = R.layout.activity_version_intro

    private lateinit var mUiLoader: UILoader
    private lateinit var mContentBinding: LayoutRefreshRecyclerviewBinding

    private var mClickVersion: VersionIntroModel? = null
    private val mAdapter: VersionPagingAdapter by lazy {
        VersionPagingAdapter {
            //获取版本详情
            mClickVersion = it
            mViewModel.getVersionContent(it.id)
        }
    }

    companion object {
        fun jump() {
            ARouter.getInstance().build(Router.Ui.VersionIntroActivity).navigation()
        }
    }

    override fun initView() {
        super.initView()
        initToolbar()
        initUILoader()
        initAdapter()
    }

    private fun getHtmlData(bodyHTML: String): String {
        val head = ("<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:100%; height:auto;}*{margin:0px 5px 0px 5px;}</style>"
                + "</head>")
        return "<html>$head<body>$bodyHTML</body></html>"
    }

    private fun initUILoader() {

        mViewModel.mVersionContentEvent.observeWithLifecycle(this) {
            when (it) {
                is Loading -> {
                    setLoadingViewVisible(true)
                }
                is Success -> {
                    setLoadingViewVisible(false)
                    CommonWebActivity.jump(mClickVersion?.title ?: "版本更新说明", richText = getHtmlData(mViewModel.mVersionContent))
                }
                is Failed -> {
                    setLoadingViewVisible(false)
                }
                else -> {

                }
            }
        }
        mContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_refresh_recyclerview,
            null,
            false
        )
        mUiLoader = object : UILoader(this) {
            override fun loadSuccessView(container: ViewGroup?): View {
                return mContentBinding.root
            }
        }.apply { updateState(UIState.LOADING) }

        mBinding.layoutContent.addView(mUiLoader)
    }

    private fun initAdapter() {
        (mContentBinding.rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mContentBinding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mContentBinding.rv.adapter = mAdapter.run {
            withLoadStateFooter(PagingFooterAdapter(::retry))
        }

    }

    override fun initListener() {
        mAdapter.loadStateFlow.observeWithLifecycle(this, Lifecycle.State.CREATED) {
            //未初始化状态
            when (it.source.refresh) {
                is LoadState.Loading -> {
                    if (mAdapter.itemCount == 0) {
                        mUiLoader.updateState(UIState.LOADING)
                    }
                }
                is LoadState.NotLoading -> {
                    mUiLoader.updateState(UIState.SUCCESS)
                }
                is LoadState.Error -> {
                    val e = (it.refresh as LoadState.Error).error as ApiException
                    handleInitPageError(e)
                }
            }
            mContentBinding.lRefresh.finishRefresh()
        }

        mViewModel.versionPageEvent.observeWithLifecycle(this) {
            mAdapter.submitData(it)
        }

        mUiLoader.setOnRetryClickListener {
            mAdapter.retry()
        }


        mContentBinding.lRefresh.setOnRefreshListener {
            //手动刷新
            mAdapter.refresh()
        }
    }

    private fun handleInitPageError(e: ApiException) {
        when (e) {
            is LoginTimeoutException -> {
//                postEvent(TokenInvalidEvent(), 500)
                ToastUtils.showShort(this, e.message)
                mUiLoader.updateState(UIState.EMPTY)
            }
            is ResponseEmptyException -> {
                mUiLoader.updateState(UIState.EMPTY)
            }
            else -> {
                mUiLoader.updateState(UIState.NETWORK_ERROR)
            }
        }

    }


    override fun initData() {

    }

    fun initToolbar() {
        mBinding.appbar.toolbarTitle.text = "更新介绍"
    }
}

@HiltViewModel
class VersionIntroViewModel @Inject constructor(
) : BaseViewModel() {

    @Inject
    lateinit var apiService: ApiService

    val mVersionContentEvent = MutableStateFlow<RequestEvent>(Default)

    var mVersionContent = ""

    val versionPageEvent
        get() = queryPostPage()
            .shareIn(viewModelScope, SharingStarted.Lazily)

    private fun queryPostPage() = Pager(
        config = globalPagingConfig,
        pagingSourceFactory = { VersionPageSource(apiService) })
        .flow

    fun getVersionContent(id: String) {
        execute(
            action = apiGetVersionContent(id),
            onStart = {
                mVersionContentEvent.value = Loading
            },
            onFinished = {
                mVersionContent = it.data?.content ?: ""
                mVersionContentEvent.value = Success
            },
            onError = {
                mVersionContentEvent.value = Failed(it)
            }
        )
    }

    private fun apiGetVersionContent(id: String) = flow {
        emit(apiService.getAppVersionIntro(id))
    }
}