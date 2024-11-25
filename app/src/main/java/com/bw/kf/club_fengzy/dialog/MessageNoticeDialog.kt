package com.bw.kf.club_fengzy.dialog

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bw.kf.club_fengzy.R

class MessageNoticeDialog(
    private val title: String? = null,
    private val message: SpannableStringBuilder? = null,
    private val onConfirmClick: (() -> Unit)? =null,
    private val onCancelClick: (()->Unit)? = null,
    private val onlyConfirmButton: Boolean = false,
    private val confirmText: String = "确定",
    private val cancelText: String = "取消",
    private val cancelable: Boolean = false,
    private val alignment: Int = Gravity.CENTER,
    private val confirmClickDismiss: Boolean = true

) : DialogFragment(){

    constructor(
        title: String?=null,
        message: String?= null,
        onConfirmClick: (() -> Unit)? = null,
        onCancelClick: (() -> Unit)? = null,
        onlyConfirmButton: Boolean = false,
        confirmText: String = "确定",
        cancelText: String = "取消",
        cancelable: Boolean = false,
        alignment: Int = Gravity.CENTER,
        confirmClickDismiss: Boolean = true
    ): this(
        title,
        SpannableStringBuilder(message) ,
        onConfirmClick,
        onCancelClick,
        onlyConfirmButton ,
        confirmText,
        cancelText,
        cancelable,
        alignment,
        confirmClickDismiss
    )

    companion object{
        val TAG: String = MessageNoticeDialog::class.java.simpleName
    }

    private lateinit var mParentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE , R.style.MessageNoticeDialog)
    }

    override fun onStart() {
        super.onStart()
        val window =dialog!!.window
        val wlp = window!!.attributes
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        wlp.gravity = Gravity.CENTER
        wlp.width = (dm.widthPixels *0.72).toInt()
        window.attributes = wlp
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mParentView = layoutInflater.inflate(R.layout.dialog_message_notice , container , false)

        return mParentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.setCancelable(cancelable)
        dialog?.setCanceledOnTouchOutside(cancelable)

        //设置内容
        mParentView.findViewById<TextView>(R.id.tv_message).apply {
            text =message
            gravity = alignment
            highlightColor = Color.TRANSPARENT
            movementMethod = LinkMovementMethod.getInstance()
        }

        mParentView.findViewById<TextView>(R.id.btn_confirm).text =confirmText
        mParentView.findViewById<TextView>(R.id.btn_cancel).text = cancelText

        if(title == null){
            mParentView.findViewById<View>(R.id.tv_title).visibility = View.GONE
        }else{
            mParentView.findViewById<TextView>(R.id.tv_title).text = title
        }

        if(onlyConfirmButton){
            mParentView.findViewById<View>(R.id.btn_cancel).visibility = View.GONE
            mParentView.findViewById<View>(R.id.line).visibility = View.GONE
        }else{
            mParentView.findViewById<View>(R.id.btn_cancel).visibility = View.VISIBLE
            mParentView.findViewById<View>(R.id.line).visibility = View.VISIBLE
            mParentView.findViewById<View>(R.id.btn_cancel).setOnClickListener{
                onCancelClick?.invoke()
                dismiss()
            }
        }

        mParentView.findViewById<View>(R.id.btn_confirm).setOnClickListener{
            onConfirmClick?.invoke()
            if(confirmClickDismiss){
                dismiss()
            }
        }

    }

    fun show(fragmentManger: FragmentManager){
        if(fragmentManger.findFragmentByTag(TAG)?.isVisible == true){
            return
        }
        this.show(fragmentManger , TAG)
    }

}