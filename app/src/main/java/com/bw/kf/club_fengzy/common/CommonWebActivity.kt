package com.bw.kf.club_fengzy.common

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.bw.kf.club_fengzy.BuildConfig
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.base.BaseToolbarActivity
import com.bw.kf.club_fengzy.base.BlankViewModel
import com.bw.kf.club_fengzy.databinding.ActivityCommonWebBinding
import com.bw.kf.club_fengzy.util.ARouterUtil
import com.bw.kf.club_fengzy.util.JLogUtil


@Route(path = Router.Ui.CommonWebActivity)
class CommonWebActivity: BaseToolbarActivity<BlankViewModel, ActivityCommonWebBinding>() {
    override val mViewModel: BlankViewModel by viewModels()
    override val mLayoutResId: Int= R.layout.activity_common_web

    companion object{
        private var mTitle = ""
        private var url = ""
        private var richText = ""
        private var userWebTitle = false
        private var jump4pay = false
        private var exitBack = false
        private var fullscreen = false

        /**
         * 用户协议
         */
        fun jumpUserAgreement(title: String = "用户协议"){
            this.mTitle = title
            this.url = "${BuildConfig.H5_HOST}mobile/agreement?.type=2"
            this.fullscreen = false
            this.exitBack = false
            this.userWebTitle = false
            JLogUtil.d("CommonWebActivity called with url = $url")
            ARouterUtil.jump(Router.Ui.CommonWebActivity)
        }

    }

    override fun initListener() {
    }

    override fun initData() {
        if(url.isNotEmpty()){
            mBinding.webView.loadUrl(url)
        }else if(richText.isNotEmpty()){
            mBinding.webView.loadDataWithBaseURL(null , richText , "text/html" , "utf-8" , null)
        }
    }

    override fun initToolbar(mToolbar: Toolbar?) {
        //设置标题
        mBinding.customToolbar.toolbarTitle.text = mTitle
        if(exitBack) {
            mBinding.customToolbar.toolbarRightIcon.visibility = View.VISIBLE
            mBinding.customToolbar.toolbarRightIcon.setImageDrawable(ContextCompat.getDrawable(this , R.mipmap.icon_exit))
        }else{
            mBinding.customToolbar.toolbarRightIcon.visibility = View.GONE
        }
    }

}