package com.bw.kf.club_fengzy.ui.main.moto


import android.graphics.Color
import android.graphics.Paint
import android.icu.text.Transliterator.Position
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.base.BaseFragment
import com.bw.kf.club_fengzy.base.observeWithLifecycle
import com.bw.kf.club_fengzy.databinding.FragmentMotoCircleV3Binding
import com.bw.kf.club_fengzy.databinding.LayoutCustomTabViewBinding
import com.bw.kf.club_fengzy.helper.SeriesPopupMenuRvHelper
import com.bw.kf.club_fengzy.model.MotoCircleNavigationItem
import com.bw.kf.club_fengzy.state.Failed
import com.bw.kf.club_fengzy.state.Success
import com.bw.kf.club_fengzy.tintDrawable
import com.bw.kf.club_fengzy.ui.main.MainViewModel
import com.bw.kf.club_fengzy.ui.main.moto.blank.BlankListFragment
import com.bw.kf.club_fengzy.ui.main.moto.info.InformationListFragment
import com.bw.kf.club_fengzy.ui.main.moto.post.PostListFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
@ActivityScoped
class MotoCircleV3Fragment @Inject constructor() : BaseFragment<MainViewModel , FragmentMotoCircleV3Binding>() {
    override val mLayoutResId: Int = R.layout.fragment_moto_circle_v3
    override val mViewModel: MainViewModel by activityViewModels()
    private var mSelectedPosition: Int = 0
    private lateinit var mPostAdapter: ScreenSlidePagerAdapter
    private var mediator: TabLayoutMediator? = null
    private val aa: Int=0
    private var bb : Int=1
    override fun initView() {
        initStatusBar()
    }

    override fun initListener() {
        mViewModel.navigationState.observeWithLifecycle(this){
            when(it){
                is Success->{
                    handleNavigationData()
                }
                is Failed->{
                }
                else->{}
            }
        }
    }
    private fun handleNavigationData(){
        initViewPager()
    }

    private fun initViewPager(){
        //默认选中帖子
        mPostAdapter = ScreenSlidePagerAdapter(mViewModel.mNavigationItem , MotoCircleModule.Post , this)
        mBinding.vp.adapter = mPostAdapter

        //预加载机制
        mBinding.vp.offscreenPageLimit = 1

        //翻页监听
        mBinding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                mSelectedPosition = position
                //设置tabLayout效果
                setTabViewStyle(position)
            }
        })

        mediator = TabLayoutMediator(
            mBinding.tabLayout,
            mBinding.vp,
            true,
            true
        ){tab ,position ->//这里自定义TabView
            val tabView = DataBindingUtil.inflate<LayoutCustomTabViewBinding>(
                layoutInflater,
                R.layout.layout_custom_tab_view,
                null,
                false
            )
            if(position >= mViewModel.mNavigationItem.size){
                return@TabLayoutMediator
            }
            val navigation = mViewModel.mNavigationItem[position]
            tabView.tab.text = navigation.title
            if(navigation.modelList == null || !navigation.modelList.any{ it.checked }){
                tabView.tab.text = mViewModel.mNavigationItem[position].title
            }else{
                tabView.tab.text = navigation.modelList.find { it.checked }?.name
            }
            tabView.ivArrow.visibility = if(navigation.modelList == null) View.GONE else View.VISIBLE
            tabView.lTab.setOnClickListener {
                if(!tab.isSelected){
                    tab.select()
                    if(navigation.modelList != null){
                        Handler(Looper.getMainLooper()).postDelayed({} , 400)
                    }
                }else{
                    if(navigation.modelList!=null){
                    }
                }
            }
            tab.customView = tabView.root
        }
        mediator?.attach()
    }

     private fun setTabViewStyle(defaultPosition: Int) {
         mViewModel.mNavigationItem.forEachIndexed { index, _ ->
             mBinding.tabLayout.run {
                 val binding = getTabAt(index)?.customView?.let {
                     DataBindingUtil.getBinding<LayoutCustomTabViewBinding>(it)
                 }
                 if(defaultPosition == index){
                     binding?.tab?.setTextColor(
                         ContextCompat.getColor(
                             requireContext(),
                             R.color.color_d8000f
                         )
                     )
                     binding?.tab?.paint?.style = Paint.Style.FILL_AND_STROKE
                     val drawable = ContextCompat.getDrawable(context , R.mipmap.ic_triangle)
                     val tintDrawable = tintDrawable(drawable , Color.parseColor("#FF919191"))
                     binding?.ivArrow?.setImageDrawable(tintDrawable)
                     binding?.tab?.paint?.strokeWidth = 0.8f
                 }else{
                     binding?.tab?.setTextColor(
                         ContextCompat.getColor(
                             requireContext() ,
                             R.color.color_101010
                         )
                     )
                     binding?.tab?.paint?.style = Paint.Style.FILL_AND_STROKE
                     val drawable = ContextCompat.getDrawable(context , R.mipmap.ic_triangle)
                     val tintDrawable = tintDrawable(drawable , Color.parseColor("#FF919191"))
                     binding?.ivArrow?.setImageDrawable(tintDrawable)
                     binding?.tab?.paint?.strokeWidth = 0f
                 }
             }
         }
     }

    override fun initData() {
        mViewModel.getFixedNavigationItem()
    }

    private fun initStatusBar(){
        immersionBar{
            this.statusBarDarkFont(true)
            this.statusBarColor(R.color.white)
        }
    }

    inner class ScreenSlidePagerAdapter(private val item: List<MotoCircleNavigationItem> ,
                                        private val module:MotoCircleModule ,
                                        fa:Fragment): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = item.size

        override fun getItemId(position: Int): Long {
            val navigation = item[position]
            val checkModel = navigation.modelList?.find { it.checked }
            if(checkModel != null){
                return checkModel.modelId.toLong()
            }
            return item[position].navigationId.toLong()
        }

        override fun createFragment(position: Int): Fragment {
            val navigation = item[position]
            return when(navigation.type){
                //热门
                1-> PostListFragment.newInstance()//TODO 待开发
                //帖子
                2->BlankListFragment.newInstance()
                //资讯
                3-> InformationListFragment.newInstance()
                //关注
                4-> PostListFragment.newInstance()
                else ->{
                    //关注
                    BlankListFragment.newInstance()
                }
            }

        }

    }

    private val popupMenRvHelper by lazy {
        SeriesPopupMenuRvHelper(layoutInflater){navigationId , modelItem ->
            val clickNavigation = mViewModel.mNavigationItem.find { it.navigationId == navigationId }
            clickNavigation?.modelList!!.forEach{
                it.checked = modelItem.modelId == it.modelId
            }
            mPostAdapter?.notifyDataSetChanged()
            setTabViewStyle(mSelectedPosition)
        }
    }

}

sealed class MotoCircleModule{
    object Post: MotoCircleModule()
    object Article: MotoCircleModule()
    object GUIDE: MotoCircleModule()
    object Information: MotoCircleModule()
}