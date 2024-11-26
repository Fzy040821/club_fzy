package com.bw.kf.club_fengzy.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bw.kf.club_fengzy.databinding.LayoutVersionItemBinding
import com.bw.kf.club_fengzy.model.VersionIntroModel


/**
 *@author Wcj
 *@description
 *@date 2022/12/1 16:57
 */
class VersionPagingAdapter(private val onItemClick: (VersionIntroModel) -> Unit) : PagingDataAdapter<VersionIntroModel, VersionPagingAdapter.VersionViewHolder>(COMPARATOR) {

    class VersionViewHolder(binding: LayoutVersionItemBinding) : RecyclerView.ViewHolder(binding.root)
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<VersionIntroModel>() {
            override fun areItemsTheSame(oldItem: VersionIntroModel, newItem: VersionIntroModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: VersionIntroModel, newItem: VersionIntroModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: VersionViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<LayoutVersionItemBinding>(holder.itemView)
        val version = getItem(position)
        version ?: return
        binding?.version = version
        binding?.root?.setOnClickListener {
            onItemClick.invoke(version)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionViewHolder {
        val binding = LayoutVersionItemBinding.inflate(LayoutInflater.from(parent.context))
        return VersionViewHolder(binding)
    }
}