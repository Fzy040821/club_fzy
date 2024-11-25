package com.bw.kf.club_fengzy.ui.main.moto.post

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.databinding.LayoutPostItemBinding
import com.bw.kf.club_fengzy.model.PostModel

class PostPagingAdapter: PagingDataAdapter<PostModel , PostViewHolder>(POST_COMPARATOR) {
    private lateinit var mContext: Context

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean =
                oldItem.postsId == newItem.postsId

            override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<LayoutPostItemBinding>(holder.itemView)
        val theItem = getItem(position)
        if(theItem != null){
            holder.initView(theItem , position)
            binding?.post =theItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        mContext = parent.context
        val binding: LayoutPostItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.layout_post_item,
            parent,
            false
        )
        return PostViewHolder(binding.root)
    }

}