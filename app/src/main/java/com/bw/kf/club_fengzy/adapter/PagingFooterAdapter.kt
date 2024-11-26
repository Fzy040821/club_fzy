package com.bw.kf.club_fengzy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.databinding.LayoutPagingFooterBinding


/**
 *@author Wcj
 *@description
 *@date 2022/7/19 9:33
 */
class PagingFooterAdapter(private val retryBlock: (() -> Unit)? = null) : LoadStateAdapter<PagingFooterViewHolder>() {


    override fun onBindViewHolder(holder: PagingFooterViewHolder, loadState: LoadState) {
        val binding = DataBindingUtil.getBinding<LayoutPagingFooterBinding>(holder.itemView)
        binding?.loadState = loadState
        if (loadState is LoadState.Error) {
            holder.itemView.setOnClickListener {
                retryBlock?.invoke()
            }
        }
    }


    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading ||
                loadState is LoadState.Error ||
                (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingFooterViewHolder {
        return PagingFooterViewHolder(
            LayoutPagingFooterBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_paging_footer, parent, false)
            )
        )
    }
}

class PagingFooterViewHolder(val binding: LayoutPagingFooterBinding) : RecyclerView.ViewHolder(binding.root)