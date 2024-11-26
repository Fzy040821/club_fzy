package com.bw.kf.club_fengzy.ui.main.moto.post

import android.graphics.Rect
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.base.BaseFragment
import com.bw.kf.club_fengzy.base.observeWithLifecycle
import com.bw.kf.club_fengzy.databinding.FragmentPostListBinding
import com.bw.kf.club_fengzy.databinding.LayoutRefreshRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class PostListFragment : BaseFragment<PostListViewModel , FragmentPostListBinding>() {

    private var category: Int? = null
    private var vehicleModelId: String? = null
    private var series: String? = null
    private var keyword: String? = null
    private var member: String? = null
    private var ifMy: Int? = null
    private var ifFocus: Int? = null
    private var ifCollection : Boolean = false
    private var showAuthorLabel: Boolean = true
    private var myPagingJob: Job? = null
    private var mySharePostId: String = ""

    override val mPageCanBack: Boolean = false

    companion object{
        fun newInstance() = PostListFragment()
    }

    override val mLayoutResId: Int = R.layout.fragment_post_list

    override val mViewModel: PostListViewModel by viewModels()
    private lateinit var mContentBinding: LayoutRefreshRecyclerviewBinding//内容绑定

    override fun initView() {
        mContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_refresh_recyclerview,
            null,
            false
        )
        mBinding.layoutContent.addView(mContentBinding.root)
        initAdapter()//初始化适配器
    }

    override fun initListener() {
    }

    private val mPostPagingAdapter by lazy {
        PostPagingAdapter()
    }

    private fun initAdapter(){
        val layoutManager: LinearLayoutManager = object :LinearLayoutManager(context){
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            override fun isAutoMeasureEnabled(): Boolean {
                return true
            }
        }

        layoutManager.isAutoMeasureEnabled = true
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mContentBinding.rv.layoutManager =layoutManager

        //数据
        mContentBinding.rv.adapter = mPostPagingAdapter

        mContentBinding.rv.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = 10
            }
        })
    }

    override fun initData() {
        setPagingObserver(category , vehicleModelId , series ,keyword , member , ifMy , ifFocus)
    }

    //重置分页参数
    private fun setPagingObserver(
        category: Int?,
        vehicleModelId: String?,
        series: String?,
        keyword: String?,
        member: String?,
        ifMy: Int?,
        ifFocus: Int?,
    ) {
        if(myPagingJob?.isActive == true){
            myPagingJob!!.cancel()
        }
        this.category = category
        this.vehicleModelId = vehicleModelId
        this.series = series
        this.keyword = keyword
        this.member = member
        this.ifFocus = ifFocus
        this.ifMy = ifMy
        mViewModel.postPageEvent2.observeWithLifecycle(this){
            mPostPagingAdapter.submitData(it)
        }
    }


}