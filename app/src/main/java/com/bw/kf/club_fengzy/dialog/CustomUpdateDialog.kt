package com.bw.kf.club_fengzy.dialog

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.base.BaseActivity
import com.bw.kf.club_fengzy.databinding.DialogCustomAppUpdateBinding
import com.bw.kf.club_fengzy.model.UpdateModel
import com.bw.kf.club_fengzy.util.OnClickHelper

import java.io.File

/**
 * Created by ZengCS on 2022/7/28.
 * E-mail:zengcs@vip.qq.com
 * Add:中国成都
 * Desc: 正在审核进度框
 */
class CustomUpdateDialog(
    private val activity: BaseActivity<*, *>,
    private val updateModel: UpdateModel
) : BaseCustomDialog() {
    companion object {
        var hasNewVersion: Boolean = false
    }

    override fun cancelAble() = false

    private val mBinding by lazy {
        DialogCustomAppUpdateBinding.inflate(LayoutInflater.from(activity))
    }

    fun show(): AlertDialog? {
        mBinding.item = updateModel

        val dialog = showCustomDialog(activity, mBinding.root)

        mBinding.btnCancel.setOnClickListener {
            if (OnClickHelper.canClick()) {
                dismissDialog(dialog)
            }
        }

        mBinding.btnConfirm.setOnClickListener {
            if (OnClickHelper.canClick()) {
                dismissDialog(dialog)
                forceUpdate()
            }
        }

        mBinding.btnConfirmSingle.setOnClickListener {
            if (OnClickHelper.canClick()) {
                forceUpdate()
            }
        }

        mBinding.btnCancel.setOnClickListener {
            dismissDialog(dialog)
        }

        return dialog
    }

    private fun forceUpdate() {
        val manager = DownloadManager.Builder(activity).run {
            apkUrl(updateModel.downloadUrl!!)
            apkName("app_new_version.apk")
            enableLog(false)
            smallIcon(R.mipmap.app_icon)
            showBgdToast(false)
            showNotification(false)
            onDownloadListener(object : OnDownloadListener {
                override fun cancel() {
                    activity.setLoadingViewVisible(false)
                    if (updateModel.ifForce()) {
                        mBinding.btnConfirmSingle.text = "确认"
                        mBinding.btnConfirmSingle.isEnabled = true
                    }
                }

                override fun done(apk: File) {
                    activity.setLoadingViewVisible(false)
//                    ToastUtils.showLong(activity, "下载成功")
                    println("下载成功")
                    if (updateModel.ifForce()) {
                        mBinding.btnConfirmSingle.text = "重新下载"
                        mBinding.btnConfirmSingle.isEnabled = true
                    }
                }

                override fun downloading(max: Int, progress: Int) {
                    val percent = ((progress.toFloat() / max.toFloat()) * 100).toInt()
                    if (updateModel.ifForce()) {
                        mBinding.btnConfirmSingle.text = "正在下载...${percent}%"
                        mBinding.btnConfirmSingle.isEnabled = false
                    } else
                        activity.setLoadingViewVisible(true, "正在下载...${percent}%")
                }

                override fun error(e: Throwable) {
                    activity.setLoadingViewVisible(false)
//                    ToastUtils.showLong(activity, "下载出错了")
                    println("下载出错了")
                    if (updateModel.ifForce()) {
                        mBinding.btnConfirmSingle.text = "确认"
                        mBinding.btnConfirmSingle.isEnabled = true
                    }
                }

                override fun start() {
                    activity.setLoadingViewVisible(true, "正在下载...")
                    if (updateModel.ifForce()) {
                        mBinding.btnConfirmSingle.text = "正在下载..."
                        mBinding.btnConfirmSingle.isEnabled = false
                    }
                }
            })
            apkVersionName(updateModel.latestVersion)
            build()
        }
        manager.download()
    }
}