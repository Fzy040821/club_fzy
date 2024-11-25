package com.bw.kf.club_fengzy.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.adapter.CommonVDBRecyclerAdapter
import com.bw.kf.club_fengzy.databinding.ItemPopupMenuRowBinding
import com.bw.kf.club_fengzy.databinding.LayoutPopupNavigationBinding
import com.bw.kf.club_fengzy.model.CommonPopupMenu
import com.bw.kf.club_fengzy.model.ModelItem
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

class SeriesPopupMenuRvHelper(private val layoutInflater: LayoutInflater , private val onSelect:((String , ModelItem)->Unit)) {
    private var mAdapter: CommonVDBRecyclerAdapter<ModelItem , ItemPopupMenuRowBinding>? = null
    private var mItems = ArrayList<ModelItem>()
    private var mNavigationId: String = ""

    fun bindItems(id:String , list: List<ModelItem>){
        this.mNavigationId = id
        list.last().last= true
        mItems.clear()
        mItems.addAll(list)
    }

    private val mBinding by lazy {
        LayoutPopupNavigationBinding.inflate(layoutInflater)
    }

    private val mPopupWindow by lazy {
        PopupWindow().apply {
            width = ViewGroup.LayoutParams.WRAP_CONTENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            isFocusable = false
            isOutsideTouchable = false
            contentView = mBinding.root
        }
    }

    fun showAgain(view: View){
        this.showAsDropDown(view)
    }

    private fun showAsDropDown(v: View) {

    }

    fun show(view: View): SeriesPopupMenuRvHelper{
        this.showAsDropDown(view)
        initAdapter()
        return this
    }

    private fun initAdapter() {
        if(mAdapter == null){
            mAdapter = object : CommonVDBRecyclerAdapter<ModelItem , ItemPopupMenuRowBinding>(R.layout.item_popup_menu_row , mItems){
                override fun convert(
                    holder: BaseDataBindingHolder<ItemPopupMenuRowBinding>,
                    item: ModelItem,
                ) {
                    holder.dataBinding?.apply {
                        val commonPopupMenu = CommonPopupMenu(name = item.name , checked = item.checked, last = item.last , tag = item.modelId)
                        this.item = commonPopupMenu
                        tvMenu.isSelected = item.checked
                        executePendingBindings()
                    }
                }
            }
            mBinding.rvPopupMenu.adapter = mAdapter
            mAdapter!!.setOnItemClickListener{adapter , _ ,position ->
                val target = mItems[position]
                target.checked= true
                adapter.notifyDataSetChanged()
                onSelect?.invoke(mNavigationId , target)
                this@SeriesPopupMenuRvHelper.mPopupWindow.dismiss()
            }
        }else{
            mAdapter?.notifyDataSetChanged()
        }
    }

}