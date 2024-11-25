package com.bw.kf.club_fengzy.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.bw.kf.club_fengzy.R

class MaxRecyclerView: RecyclerView {
    private var mMaxHeight: Int = 210

    constructor(context: Context): super(context){}


    constructor(context: Context , attrs: AttributeSet?): super(context ,attrs){
        init(context , attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context , attrs, defStyleAttr){
        init(context , attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val arr =context.obtainStyledAttributes(attrs , R.styleable.MaxRecyclerView)
        mMaxHeight = arr.getLayoutDimension(R.styleable.MaxRecyclerView_customMaxHeight , mMaxHeight)
        arr.recycle()
    }

    fun setMaxHeight(height: Int){
        this.mMaxHeight = height
        requestLayout()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        val height = measuredHeight
        if(height > mMaxHeight){
            setMeasuredDimension(widthSpec , mMaxHeight)
        }
    }

}