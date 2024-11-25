package com.bw.kf.club_fengzy.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.Router
import com.bw.kf.club_fengzy.databinding.ActivityGuideV2Binding
import com.bw.kf.club_fengzy.ui.main.MainActivity
import com.gyf.immersionbar.ktx.immersionBar
import kotlin.math.roundToInt

@Route(path = Router.Ui.GuideActivity)
class GuideActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityGuideV2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_guide_v2)
        showIndicator()
        //沉浸式状态栏
        immersionBar {
            this.statusBarDarkFont(true)
        }

        mBinding.vp.adapter = GuidePageAdapter()
        mBinding.vp.offscreenPageLimit = 1

        mBinding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when(position){
                    0 -> {
                        mBinding.guide1.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_checked)
                        mBinding.guide2.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                        mBinding.guide3.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                    }
                    1 -> {
                        mBinding.guide1.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                        mBinding.guide2.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_checked)
                        mBinding.guide3.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                    }
                    2 -> {
                        mBinding.guide1.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                        mBinding.guide2.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_uncheck)
                        mBinding.guide3.background = ContextCompat.getDrawable(this@GuideActivity, R.drawable.bg_indicator_checked)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                if(position == 2){
                    hideIndicator()
                }else{
                    showIndicator()
                }
            }

        })

        mBinding.btnEnter.setOnClickListener{
            ARouter.getInstance()
                .build(Router.Ui.MainActivity)
                .withTransition(R.anim.right_in , R.anim.left_out)
                .navigation(this)
            //navigateToMainActivity()
//            val intent: Intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            ActivityCompat.finishAffinity(this)
        }

    }

//    private fun navigateToMainActivity() {
//        val postcard = ARouter.getInstance().build(Router.Ui.MainActivity)
//            .withTransition(R.anim.right_in, R.anim.left_out)
//        postcard.navigation(this, object : NavigationCallback {
//            override fun onFound(postcard: Postcard?) {
//                // 路径被找到
//            }
//
//            override fun onLost(postcard: Postcard?) {
//                // 路径未找到
//                Toast.makeText(this@GuideActivity, "跳转失败", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onArrival(postcard: Postcard?) {
//            }
//
//            override fun onInterrupt(postcard: Postcard?) {
//            }
//
//            fun onError(postcard: Postcard?, e: Throwable?) {
//                // 发生错误
//                Toast.makeText(this@GuideActivity, "跳转发生错误", Toast.LENGTH_SHORT).show()
//            }
//
//            fun onComplete(postcard: Postcard?) {
//                // 跳转完成
//                ActivityCompat.finishAffinity(this@GuideActivity)
//            }
//        })
//    }


    private fun hideIndicator(){
        mBinding.lGuide.visibility = View.INVISIBLE
        mBinding.btnEnter.visibility = View.VISIBLE
    }

    private fun showIndicator(){
        mBinding.lGuide.visibility = View.VISIBLE
        mBinding.btnEnter.visibility = View.INVISIBLE
    }

    private inner class GuidePageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val guidePicRes = listOf(R.mipmap.bg_guide_1 , R.mipmap.bg_guide_2 , R.mipmap.bg_guide_3)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object :RecyclerView.ViewHolder(ImageView(parent.context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
            }){}
        }

        override fun getItemCount(): Int =guidePicRes.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            //按照图片比例截取上半部分的图片
            scaleImage(holder.itemView , guidePicRes[position])
        }
    }

    fun scaleImage(view: View, drawableResId: Int) {
        //获取屏幕的宽高
        val outSize = Point()
        window.windowManager.defaultDisplay.getSize(outSize)
        //解析将要被处理的图片
        val resourceBitMap: Bitmap = BitmapFactory.decodeResource(resources , drawableResId) ?:return
        //使用图片的缩放比例计算将要放大的图片高度
        var bitmapScaledHeight = 0
        var bitmapScaleWidth = 0
        bitmapScaledHeight = (resourceBitMap.height * outSize.x * 1.0f / resourceBitMap.width).roundToInt()
        bitmapScaleWidth =outSize.x

        if(bitmapScaledHeight < outSize.y){
            //已宽度为基准 , 缩放后的图片高度小于屏幕高度,修改为高度为基准
            bitmapScaledHeight = outSize.y
            bitmapScaleWidth = (resourceBitMap.width * outSize.y * 1.0f / resourceBitMap.height).roundToInt()
        }

        //已屏幕的宽度为基准 , 如果图片的宽度比屏幕宽 , 则等比缩小 , 如果窄 , 则放大
        val scaleBitmap: Bitmap = Bitmap.createScaledBitmap(resourceBitMap , bitmapScaleWidth , bitmapScaledHeight , false)
        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
            override fun onPreDraw(): Boolean {
                //这里防止图片重复创建 , 避免申请不必要的内存空间
                if(scaleBitmap.isRecycled){//必须返回true
                    return true
                }
                //当UI绘制完毕 , 我们对图片进行处理
                val viewHeight = view.measuredHeight
                //计算将要剪裁的图片的顶部及底部的偏移量
                val offset: Int = (scaleBitmap.height - viewHeight) / 2
                //对图片以中心进行剪裁 , 剪裁出来的图片就是非常适合做引导页的图片了
                val finallyBitmap: Bitmap = if(scaleBitmap.height - offset * 2 < scaleBitmap.height){
                    Bitmap.createBitmap(
                        scaleBitmap , 0 ,0 , scaleBitmap.width,
                        scaleBitmap.height-offset * 2
                    )
                }else{
                    Bitmap.createBitmap(
                        scaleBitmap , 0, 0 , scaleBitmap.width,
                        scaleBitmap.height
                    )
                }

                if(finallyBitmap != scaleBitmap){
                    scaleBitmap.recycle()
                    System.gc()
                }
                //设置图片显示
                (view as ImageView).setImageBitmap(finallyBitmap)
                return true
            }
        })

    }



}