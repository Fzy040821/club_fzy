package com.bw.kf.club_fengzy.splash

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.base.BaseActivity
import com.bw.kf.club_fengzy.common.CommonWebActivity
import com.bw.kf.club_fengzy.databinding.ActivitySplashBinding
import com.bw.kf.club_fengzy.dialog.MessageNoticeDialog
import com.bw.kf.club_fengzy.getAttrValue
import com.bw.kf.club_fengzy.getVersionCode
import com.bw.kf.club_fengzy.mmkv.AppMMKV
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.coroutines.launch
import kotlin.math.log

class SplashActivity : BaseActivity<SplashViewModel , ActivitySplashBinding>() {
    companion object{
        private const val SPLASH_MAX_DELAY: Long =1_500
    }

    override val mViewModel: SplashViewModel by viewModels()

    override val mLayoutResId: Int = R.layout.activity_splash
    private val mHandler by lazy {
        object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                if(AppMMKV.appGuide){
                    toGuide()
                }else{
                    toMain()
                }
            }
        }
    }


    /**
     * 跳转至引导页
     */
    private fun toGuide(){
        ARouter.getInstance()
            .build(Router.Ui.GuideActivity)
            .navigation(this)

        ActivityCompat.finishAffinity(this)
        Log.i("TAG", "toGuide: aaaa")
        Log.i("TAG", "toGuide: aaaa")
        Log.i("TAG", "toGuide: aaaa")
        Log.i("TAG", "toGuide: aaaa")
    }

    /**
     * 跳转至主页
     */
    private fun toMain(){
        lifecycleScope.launch {

        }
        ARouter.getInstance()
            .build(Router.Ui.MainActivity)
            .navigation(this)

        ActivityCompat.finishAffinity(this)
    }

    override fun initStateBar(){
        immersionBar{
            this.statusBarDarkFont(true)
        }
    }


    override fun initListener() {
    }

    override fun initData() {
        initStateBar()
        if(!AppMMKV.splashAgreement){
            //弹出隐私权限
            val colorMain = getAttrValue(this , R.attr.colorTheme)
            val userAgreement = "《用户协议》"
            val privacyPolicy = "《隐私协议》"
            val content =
                "宗申骑士俱乐部非常尊重用户个人的信息保护，我们依据最新的监管要求特向您说明如下：\n1.在使用 APP过程中，我们有可能会收集、使用设备标识信息用于推送车辆提醒消息。\n2.我们会一直采取严格的安全技术保护您的个人信息安全。\n3.未经您的同意，我们不会以协议约定之外的形式和渠道获取、共享或使用您的个人信息。\n您可通过阅读完整的${userAgreement}和${privacyPolicy}来了解详细信息。请您充分阅读并理解以上协议，点击同意按钮，即表示您已同意上述协议及以上约定。"

            val spannableStringBuilder = SpannableStringBuilder(content)
            val userAgreementStart = content.indexOf(userAgreement)
            val userAgreementEnd = content.indexOf(userAgreement) + userAgreement.length
            val privacyPolicyStart = content.indexOf(privacyPolicy)
            val privacyPolicyEnd = content.indexOf(privacyPolicy)+privacyPolicy.length

            //用户协议
            spannableStringBuilder.setSpan(object : ClickableSpan(){
                override fun onClick(p0: View) {
                    CommonWebActivity.jumpUserAgreement()
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ContextCompat.getColor(this@SplashActivity , colorMain)
                    ds.isUnderlineText = false
                }

            } , userAgreementStart , userAgreementEnd , Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            //隐私协议
            spannableStringBuilder.setSpan(object : ClickableSpan(){
                override fun onClick(p0: View) {
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ContextCompat.getColor(this@SplashActivity , colorMain)
                    ds.isUnderlineText = false
                }

            } , privacyPolicyStart , privacyPolicyEnd , Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            //对话框
            MessageNoticeDialog(
                title = "欢迎使用宗申骑士club",
                message = spannableStringBuilder,
                cancelText ="不同意",
                confirmText = "同意",
                alignment = Gravity.START,
                onConfirmClick ={
                    //同意隐私策略
                    AppMMKV.splashAgreement = true
                    //记录当前版本号
                    AppMMKV.lastVersionCode = getVersionCode(this)
                    mHandler.sendEmptyMessageDelayed(2, SPLASH_MAX_DELAY)
                },
                onCancelClick ={finish()}
            ).show(supportFragmentManager)
        }else{
            mHandler.sendEmptyMessageDelayed(2, SPLASH_MAX_DELAY)
        }
    }
}