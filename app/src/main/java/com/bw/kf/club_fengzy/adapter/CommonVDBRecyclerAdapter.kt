package com.bw.kf.club_fengzy.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

abstract class CommonVDBRecyclerAdapter<T ,BD: ViewDataBinding>(@LayoutRes resId: Int , data: ArrayList<T>):
    BaseQuickAdapter<T , BaseDataBindingHolder<BD>>(resId , data) , LoadMoreModule{
}

abstract class DraggableVDBRecyclerAdapter<T, BD : ViewDataBinding>(@LayoutRes resId: Int, data: ArrayList<T>) :
    BaseQuickAdapter<T, BaseDataBindingHolder<BD>>(resId, data), DraggableModule {

    }