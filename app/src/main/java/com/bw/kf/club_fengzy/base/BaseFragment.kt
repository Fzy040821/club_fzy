package com.bw.kf.club_fengzy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel , VDB: ViewDataBinding>: Fragment() {
    protected abstract val mLayoutResId: Int
    protected lateinit var mBinding: VDB
    protected abstract val mViewModel: VM
    protected lateinit var mParentView: View
    open val mPageCanBack: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater , mLayoutResId , container , false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mParentView = mBinding.root
        return mParentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()
    }

    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()

}