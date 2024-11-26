package com.bw.kf.club_fengzy.profile

import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.bw.kf.club_fengzy.BuildConfig
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.adapter.CommonVDBRecyclerAdapter
import com.bw.kf.club_fengzy.base.BaseToolbarActivity
import com.bw.kf.club_fengzy.common.CommonWebActivity
import com.bw.kf.club_fengzy.databinding.ActivityAboutUsBinding
import com.bw.kf.club_fengzy.databinding.ItemProfileMenuRowBinding
import com.bw.kf.club_fengzy.dialog.CustomUpdateDialog
import com.bw.kf.club_fengzy.mmkv.AuthMMKV
import com.bw.kf.club_fengzy.model.UpdateModel
import com.bw.kf.club_fengzy.profile.vm.BaseProfileViewModel
import com.bw.kf.club_fengzy.state.Failed
import com.bw.kf.club_fengzy.state.Loading
import com.bw.kf.club_fengzy.state.Success
import com.bw.kf.club_fengzy.util.ARouterUtil
import com.bw.kf.club_fengzy.util.ToastUtils
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by ZengCS on 2022/7/28.
 * E-mail:zengcs@vip.qq.com
 * Add:中国成都
 * Desc: 关于我们
 */
@AndroidEntryPoint
@Route(path = Router.Ui.AboutUsActivity)
class AboutUsActivity : BaseToolbarActivity<AboutUsViewModel, ActivityAboutUsBinding>() {
    override val mViewModel: AboutUsViewModel by viewModels()
    override val mLayoutResId: Int = R.layout.activity_about_us

    companion object {
        private const val TITLE = "关于我们"
        private var paramId: String = ""
        fun jump(paramId: String = "") {
            this.paramId = paramId
            ARouterUtil.jump(Router.Ui.AboutUsActivity)
        }
    }

    override fun initView() {
        super.initView()
        initMenus()
    }

    override fun initListener() {
        addCommonStateListener(mViewModel.stateListener)
        mViewModel.updateLiveData.observe(this) {
            showUpdateAppDialog(it)
        }
    }

    var customUpdateDialog: CustomUpdateDialog? = null

    //软件升级提示框
    private fun showUpdateAppDialog(updateModel: UpdateModel) {
//        if (BuildConfig.DEBUG) {
//            updateModel.appLevelId = 1001
//            updateModel.forceUpGrade = 1
//        }
        val new = updateModel.latestVersion.split(".")
        if (new.isEmpty() || new.size != 3) return
        val current = BuildConfig.VERSION_NAME.split(".")
        val needUpdate = isNeedUpdate(new,current)
        if (updateModel.latestVersion.isEmpty()
            || updateModel.downloadUrl.isNullOrEmpty()
            || !needUpdate
        ) {
            if (!updateModel.silence)
                ToastUtils.showLong(this, "当前已经是最新版本")
            return
        }


        if (updateModel.silence) {
            mItems.lastOrNull()?.hasNew = true
            mAdapter?.notifyItemChanged(mItems.lastIndex)
        } else {
            CustomUpdateDialog.hasNewVersion = true
            // 发现新版本
            customUpdateDialog = CustomUpdateDialog(
                this@AboutUsActivity,
                updateModel = updateModel
            )
            customUpdateDialog!!.show()
        }
    }

    override fun initData() {
        if (AuthMMKV.hasLogin())
            mViewModel.updateApp(silence = true)
    }


    private fun isNeedUpdate(new: List<String>, current: List<String>): Boolean {
        if (new[0].toInt() > current[0].toInt()) {
            return true
        } else if (new[0].toInt() < current[0].toInt()) {
            return false
        }
        if (new[1].toInt() > current[1].toInt()) {
            return true
        } else if (new[1].toInt() < current[1].toInt()) {
            return false
        }
        if (new[2].toInt() > current[2].toInt()) {
            return true
        } else if (new[2].toInt() < current[2].toInt()) {
            return false
        }
        return false
    }

    /** 初始化菜单-1 */
    var mAdapter: CommonVDBRecyclerAdapter<ProfileMenuItem, ItemProfileMenuRowBinding>? = null
    val mItems = ArrayList<ProfileMenuItem>()
    private fun initMenus() {
        mItems.clear()
        mItems.addAll(ProfileMenu.createAboutMenu())
        mAdapter = object : CommonVDBRecyclerAdapter<ProfileMenuItem, ItemProfileMenuRowBinding>(R.layout.item_profile_menu_row, mItems) {
            override fun convert(holder: BaseDataBindingHolder<ItemProfileMenuRowBinding>, item: ProfileMenuItem) {
                val binding = holder.dataBinding
                binding?.item = item
                binding?.containerBg?.setOnClickListener {
                    onMenuClick(item)
                }
                binding?.executePendingBindings()
            }
        }
        mBinding.rvCommon.adapter = mAdapter
    }

    private fun onMenuClick(item: ProfileMenuItem) {
        // 1、协议 {url}/mobile/agreement?type={type}  1:隐私协议  2:用户协议 3:免责协议
        when (item.id) {
            ProfileMenu.PRIVACY_AGREEMENT -> {
                // 跳转"隐私协议"
//                CommonWebActivity.jumpPrivacyAgreement()
            }
            ProfileMenu.USER_AGREEMENT -> {
                // 跳转"用户协议"
                CommonWebActivity.jumpUserAgreement()
            }
            ProfileMenu.VERSION_INTRO -> {
                //更新介绍
               VersionIntroActivity.jump()
            }
            ProfileMenu.CHECK_VERSION -> {
                //if (AuthMMKV.hasLoginWithToast(this)) {
                    if (mViewModel.updateLiveData.value != null) {
                        mViewModel.updateLiveData.value!!.silence = false
                        showUpdateAppDialog(mViewModel.updateLiveData.value!!)
                    } else
                        mViewModel.updateApp()
               // }
            }
        }
    }

    /** 初始化菜单-2 */

    override fun initToolbar(mToolbar: Toolbar?) {
        // 设置标题
        mBinding.customToolbar.toolbarTitle.text = TITLE
    }
}

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    private val mRepository: ProfileRepository
) : BaseProfileViewModel(mRepository) {
    val updateLiveData by lazy {
        MutableLiveData<UpdateModel>()
    }

    fun updateApp(silence: Boolean = false) {
        execute(action = mRepository.updateApp(),
            onStart = { if (!silence) _stateListener.emit(Loading) },
            onFinished = {
                if (!silence)
                    _stateListener.emit(Success)
                if (it.data != null) {
                    it.data?.silence = silence
                    updateLiveData.postValue(it.data)
                } else if (!silence)
                    _stateListener.emit(Failed("当前已经是最新版本"))
            },
            onError = { if (!silence) _stateListener.emit(Failed(it)) }
        )
    }
}

