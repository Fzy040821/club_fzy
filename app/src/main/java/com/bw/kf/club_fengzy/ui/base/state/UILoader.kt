package com.bw.kf.club_fengzy.ui.base.state

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.FrameLayout
import com.bw.kf.club_fengzy.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @项目名称 UILoader
 * @类名 UILoader
 * @创建时间 2021/12/11 15:15
 * @作者 钟阳
 * @描述 UI状态加载器
 */
abstract class UILoader constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {
    private var mLoadingView: View? = null
    private var mErrorView: View? = null
    private var mEmptyView: View? = null
    private var mSuccessView: View? = null
    private var mUnLoginView: View? = null
    private var mNoFollowView: View? = null
    var onLoginClick: (() -> Unit)? = null
    var mCurrentState = UIState.NONE //当前状态
    protected var mIRetryClickListener: IRetryClickListener? = null

    private var isRefresh = false

    constructor(context: Context) : this(context, null) {
        // this.setBackgroundResource(R.color.color_f4f5f7)
    }


    fun setEnableRefresh(enable: Boolean) {
        mEmptyView?.findViewById<SmartRefreshLayout>(R.id.l_refresh)?.setEnableRefresh(enable)
        mErrorView?.findViewById<SmartRefreshLayout>(R.id.l_refresh)?.setEnableRefresh(enable)
    }

    /**
     * 更新UI状态方法
     *
     * @param state UI状态
     */
    fun updateState(state: UIState) {
        if (mCurrentState != state) {
            mCurrentState = state //将当前状态赋值

            Handler().post { switchUIByState() } //更新UI
        }
    }

    /**
     * 根据当前状态切换UI
     */
    private fun switchUIByState() {
        /*成功布局*/
        if (mSuccessView == null) {
            mSuccessView = loadSuccessView(this) //获取对应布局文件
            addView(mSuccessView) //添加到父布局
        }

        if (mLoadingView == null) {
            mLoadingView = loadLoadingView()
            addView(mLoadingView)
        }

        if (mEmptyView == null) {
            mEmptyView = loadEmptyView()
            addView(mEmptyView)
        }

        if (mErrorView == null) {
            mErrorView = loadErrorView()
            addView(mErrorView)
        }

//        if (mUnLoginView == null) {
//            mUnLoginView = loadUnLoginView()
//            addView(mUnLoginView)
//        }
//
//        if (mNoFollowView == null) {
//            mNoFollowView = loadNoFollowView()
//            addView(mNoFollowView)
//        }

        //设置是否显示
        if (mCurrentState == UIState.SUCCESS && mSuccessView != null) {
            mSuccessView!!.startAnimation(alphaAniShow)
            this.setBackgroundResource(R.color.transparent)
        }
        mSuccessView!!.visibility = if (mCurrentState == UIState.SUCCESS) VISIBLE else GONE
        this.setBackgroundResource(if (mCurrentState == UIState.SUCCESS) R.color.transparent else R.color.color_f4f5f7)

        //刷新时不切换/成功时切换
        if (!isRefresh || mCurrentState == UIState.SUCCESS) {

            mLoadingView!!.visibility = if (mCurrentState == UIState.LOADING) VISIBLE else GONE
            mErrorView!!.visibility = if (mCurrentState == UIState.NETWORK_ERROR) VISIBLE else GONE
            mEmptyView!!.visibility = if (mCurrentState == UIState.EMPTY) VISIBLE else GONE
            mUnLoginView!!.visibility = if (mCurrentState == UIState.UN_LOGIN) VISIBLE else GONE
            mNoFollowView!!.visibility = if (mCurrentState == UIState.NO_FOLLOW) VISIBLE else GONE
        }

        if (mEmptyView?.visibility == View.VISIBLE)
            mEmptyView!!.findViewById<SmartRefreshLayout>(R.id.l_refresh).finishRefresh()

        if (mErrorView?.visibility == View.VISIBLE)
            mErrorView!!.findViewById<SmartRefreshLayout>(R.id.l_refresh).finishRefresh()

        isRefresh = false
    }

    /**
     * 加载成功布局文件，该方法子类必须实现
     *
     * @param container 父容器
     * @return
     */
    protected abstract fun loadSuccessView(container: ViewGroup?): View?

    /**
     * 加载空数据布局
     *
     * @return
    //     */
//    protected fun loadEmptyView(): View {
//        val emptyView = LayoutInflater.from(context).inflate(R.layout.view_empty, this, false)
//        emptyView.findViewById<View>(R.id.tv_errorView_hint).setOnClickListener { v: View? ->
//            if (mIRetryClickListener != null) {
//                mIRetryClickListener!!.onRetryClick()
//            }
//        }
//        return emptyView
//    }

    open fun loadEmptyView(): View {
        val emptyView = LayoutInflater.from(context).inflate(R.layout.view_empty_ui_with_refresh, this, false)
        emptyView.findViewById<SmartRefreshLayout>(R.id.l_refresh).setOnRefreshListener {
            if (mIRetryClickListener != null) {
                isRefresh = true
                mCurrentState = UIState.REFRESH
                mIRetryClickListener!!.onRetryClick()
            }
        }
        return emptyView
    }

    /**
     * 加载网络错误布局
     *
     * @return
     */
//    protected fun loadErrorView(): View {
//        //加载错误布局文件
//        val errorView = LayoutInflater.from(context).inflate(R.layout.view_error, this, false)
//        //找到重试按钮，为其设置点击事件
//        errorView.findViewById<View>(R.id.tv_errorView_hint).setOnClickListener { v: View? ->
//            if (mIRetryClickListener != null) {
//                mIRetryClickListener!!.onRetryClick()
//            }
//        }
//        return errorView
//    }

    protected fun loadErrorView(): View {
        //加载错误布局文件
        val errorView = LayoutInflater.from(context).inflate(R.layout.view_error_ui_with_refresh, this, false)
        //找到重试按钮，为其设置点击事件
        errorView.findViewById<SmartRefreshLayout>(R.id.l_refresh).setOnRefreshListener {
            if (mIRetryClickListener != null) {
                isRefresh = true
                mCurrentState = UIState.REFRESH
                mIRetryClickListener!!.onRetryClick()
            }
        }

        errorView.findViewById<Button>(R.id.btn_retry).setOnClickListener {
            if (mIRetryClickListener != null) {
                mIRetryClickListener!!.onRetryClick()
            }
        }
        return errorView
    }

    /**
     * 加载加载中布局文件
     *
     * @return
     */
    protected fun loadLoadingView(): View {
        return LayoutInflater.from(context).inflate(R.layout.view_loading, this, false)
    }

//    private fun loadUnLoginView(): View {
//        val binding = ViewEmptyUnLoginBinding.inflate(LayoutInflater.from(context))
//        binding.btnGoLogin.setOnClickListener {
//            onLoginClick?.invoke()
//        }
//        return binding.root
//    }

//    private fun loadNoFollowView(): View {
////        val binding = ViewEmptyNoFollowBinding.inflate(LayoutInflater.from(context))
////        binding.btnGoFollow.setOnClickListener {
////            RecommendFriendsListActivity.jump()
////        }
//        return binding.root
//    }

    /**
     * 重试点击方法
     *
     * @param listener 监听接口
     */
    fun setOnRetryClickListener(listener: IRetryClickListener?) {
        mIRetryClickListener = listener
    }

    private var alphaAniShow: AlphaAnimation? = null
    private fun alphaAnimation() {
        alphaAniShow = AlphaAnimation(0f, 1f)
        alphaAniShow!!.duration = 500
    }

    init {
        switchUIByState() //切换UI状态-当前为初始状态NONE
        alphaAnimation()

    }
}