package com.bw.kf.club_fengzy.base

import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.bw.kf.club_fengzy.R
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseToolbarActivity<VM: BaseViewModel , VDB: ViewDataBinding>: BaseActivity<VM , VDB>() {

    //appbar
    protected lateinit var mToolbar: Toolbar

    override fun initView() {
        initToolbar()
    }

    override fun initStateBar() {
        immersionBar{
            this.titleBar(mToolbar)
            this.statusBarDarkFont(true)
        }
    }

    private fun initToolbar(){
        mToolbar = mBinding.root.findViewById(R.id.toolbar)
        initToolbar(mToolbar)
        mToolbar.setNavigationOnClickListener { _ -> onBackPressed() }
    }

    protected open fun initToolbar(mToolbar: Toolbar?) {

    }

}