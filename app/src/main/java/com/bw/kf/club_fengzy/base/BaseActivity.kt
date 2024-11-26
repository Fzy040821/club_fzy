package com.bw.kf.club_fengzy.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.state.Failed
import com.bw.kf.club_fengzy.state.Loading
import com.bw.kf.club_fengzy.state.RequestEvent
import com.bw.kf.club_fengzy.state.Success
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseActivity<VM: BaseViewModel , VDB: ViewDataBinding>: AppCompatActivity() {

    abstract val mViewModel: VM
    protected lateinit var mBinding:VDB
    abstract val mLayoutResId: Int
    protected var mLoadingView: View? = null
    //rootView
    protected lateinit var mRootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        mBinding = DataBindingUtil.setContentView(this , mLayoutResId)
        initGlobalView()
        initView()
        initStateBar()
        initListener()
        initData()
    }

    abstract fun initListener()

    abstract fun initData()

    //初始化状态栏
    protected open fun initStateBar(){
        ImmersionBar.with(this).init()
    }

    open fun initView(){}

    private fun initGlobalView(){
        mRootView = window.decorView as ViewGroup
        initLoadingView()
    }
    private fun initLoadingView(){
        if(mLoadingView == null){
            mLoadingView = layoutInflater.inflate(R.layout.dialog_common_progress , mRootView, false)
            mLoadingView!!.setOnClickListener({v:View?->})
            setLoadingViewVisible(false)
            mRootView.addView(mLoadingView)
        }
    }

    fun setLoadingViewVisible(show: Boolean , msg: String = "") {
        mLoadingView?.let {
            val textView = it.findViewById<TextView>(R.id.tv_text)
            if(show && msg.isNotEmpty()){
                textView.text = msg
            }
            if(show) it.visibility = View.VISIBLE else it.visibility = View.GONE
        }
    }

    open fun addCommonStateListener(stateListener: SharedFlow<RequestEvent>) {
        stateListener.observeWithLifecycle(this, Lifecycle.State.STARTED) {
            when (it) {
                is Success -> {
                    setLoadingViewVisible(false)
                }
                is Loading -> {
                    setLoadingViewVisible(true)
                }
                is Failed -> {
                    // 商品已下架 不Toast
                    if (it.message != "商品已下架") {
//                        ToastUtils.showShort(this, it.message)
                    }
                    setLoadingViewVisible(false)
                    onLoadFailed(it.message)
                }
                else -> {}
            }
        }
    }

    open fun onLoadFailed(msg: String) {}



}