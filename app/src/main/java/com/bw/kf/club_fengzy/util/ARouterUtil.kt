package com.bw.kf.club_fengzy.util

import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.kf.club_fengzy.IntentData

object ARouterUtil {

    fun jump(page: String , id: String= "" , name: String = "" , bool: Boolean? = null){
        val navigation =ARouter.getInstance().build(page)
        if(id.isNotEmpty()){
            navigation.withString(IntentData.KEY_ID , id)
        }
        if(name.isNotEmpty()){
            navigation.withString(IntentData.KEY_NAME , name)
        }
        if(bool != null){
            navigation.withBoolean(IntentData.KEY_BOOLEAN , bool)
        }
        navigation.navigation()
    }

    fun jumpLogin(activity: AppCompatActivity){
        if(activity == null){

        }else{

        }
    }

}