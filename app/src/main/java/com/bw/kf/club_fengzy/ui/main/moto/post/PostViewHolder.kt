package com.bw.kf.club_fengzy.ui.main.moto.post

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bw.kf.club_fengzy.databinding.LayoutPostItemBinding
import com.bw.kf.club_fengzy.model.PostModel

class PostViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
    private val mBinding: LayoutPostItemBinding by lazy {
        DataBindingUtil.getBinding(this.itemView)!!
    }
    var postModel: PostModel?= null
    fun initView(post: PostModel , position: Int){
        mBinding.apply {
            postModel = post
        }
    }

}