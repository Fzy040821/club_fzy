package com.bw.kf.club_fengzy.dialog

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.DisplayMetrics
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bw.kf.club_fengzy.R


/**
 *@author Wcj
 *@description app升级
 *@date 2022/9/26 11:19
 */
class AppUpdateDialog(
    private val title: String,
    private val message: String,
    private val force: Boolean,
    private val onUpdateClick: () -> Unit
) : DialogFragment() {
    private lateinit var mParentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MessageNoticeDialog)
    }


    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        val wlp = window!!.attributes
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        wlp.gravity = Gravity.CENTER
        wlp.width = (dm.widthPixels * 0.72).toInt()
        window.attributes = wlp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mParentView = layoutInflater.inflate(R.layout.dialog_app_update, container, false)

        return mParentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        mParentView.findViewById<TextView>(R.id.tv_message).movementMethod = ScrollingMovementMethod.getInstance()
        mParentView.findViewById<TextView>(R.id.tv_title).text = title
        mParentView.findViewById<TextView>(R.id.tv_message).text = message

        if (force) {
            mParentView.findViewById<ImageView>(R.id.tv_cancel).visibility = View.GONE
        } else {
            mParentView.findViewById<ImageView>(R.id.tv_cancel).apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    dismiss()
                }
            }
        }

        mParentView.findViewById<TextView>(R.id.btn_update).setOnClickListener {
            onUpdateClick.invoke()
            dismiss()
        }
        // 显示取消按钮并添加点击事件
        mParentView.findViewById<ImageView>(R.id.tv_cancel).apply {
            visibility = View.VISIBLE
            setOnClickListener{
                dismiss()
            }
        }

    }

    fun show(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(MessageNoticeDialog.TAG)?.isVisible == true) {
            return
        }
        this.show(fragmentManager, MessageNoticeDialog.TAG)
    }
}