package com.bw.kf.club_fengzy

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import me.jessyan.autosize.utils.ScreenUtils

@HiltAndroidApp
class App: Application() {

    companion object{
        private lateinit var app: App

        fun getAppContext(): App{
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()

        app = this
        //初始化路由框架
        initARouter()
        //初始化MMKV
        initMMKV()
        //初始化极光推送
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        //屏幕适配AutoSize
        //initAutoSize()
    }

    /**
     * 屏幕适配AutoSize初始化
     */
//    private fun initAutoSize(){
//        AutoSizeConfig.getInstance()
//            .setPrivateFontScale(1f)
//            .onAdaptListener = object : onAdaptListener {
//            override fun onAdaptBefore(target: Any?, activity: Activity?) {
//                AutoSizeConfig.getInstance().screenWidth = ScreenUtils.getScreenSize(activity)[0]
//                AutoSizeConfig.getInstance().screenHeight = ScreenUtils.getScreenSize(activity)[1]
//                if(activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
//                    AutoSizeConfig.getInstance().designWidthInDp = AppConfig.DESIGN_WIDTH_IN_DP
//                    AutoSizeConfig.getInstance().designHeightInDp = AppConfig.DESIGN_HEIGHT_IN_DP
//                }else{
//                    AutoSizeConfig.getInstance().designWidthInDp = AppConfig.DESIGN_WIDTH_IN_DP
//                    AutoSizeConfig.getInstance().designWidthInDp = AppConfig.DESIGN_HEIGHT_IN_DP
//                }
//            }
//
//            override fun onAdaptAfter(target: Any?, activity: Activity?) {
//            }
//
//        }
//    }

    /**
     * MMKV初始化
     */
    private fun initMMKV(){
        if(AppConfig.MMKV_ROOT_DIR_PATH.isEmpty()){
            MMKV.initialize(this)
        }else{
            MMKV.initialize(this , AppConfig.MMKV_ROOT_DIR_PATH)
        }
    }

    /**
     * ARouter初始化
     */
    private fun initARouter(){
//        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
//        }
        ARouter.init(this)
    }


}