package com.bw.kf.club_fengzy

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.graphics.drawable.DrawableCompat
import androidx.paging.PagingConfig


val appContext: Context
    get() = App.getAppContext()

fun getAttrValue(context: Context, attrId: Int): Int{
    val typeValue = TypedValue()
    context.theme.resolveAttribute(attrId, typeValue , true)
    return typeValue.resourceId
}

fun tintDrawable(drawable: Drawable? , colors: Int): Drawable?{
    val wrappedDrawable = DrawableCompat.wrap(drawable!!).mutate()
    DrawableCompat.setTint(wrappedDrawable , colors)
    return wrappedDrawable
}

fun getVersionCode(mContext: Context):Long{
    var versionCode: Long = 0
    try {
        val packageInfo: PackageInfo = mContext.packageManager.getPackageInfo(mContext.packageName , 0)
        versionCode = if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
            packageInfo.longVersionCode
        }else{
            packageInfo.versionCode.toLong()
        }
        println("Version Code: $versionCode")
    }catch (e: PackageManager.NameNotFoundException){
        e.printStackTrace()
    }
    return versionCode
}

//全局分页的配置
const val DEFAULT_PAGE_SIZE = 10
const val INITIAL_LOAD_SIZE = 10
val globalPagingConfig: PagingConfig
    get() {

        return PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = true,
            initialLoadSize = INITIAL_LOAD_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE
        )
    }