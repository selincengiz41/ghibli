package com.selincengiz.ghibli.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.constraintlayout.motion.widget.MotionLayout

class ClickableMotionLayout: MotionLayout {
    private var mStartTime: Long = 0
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {

        if ( event?.action == MotionEvent.ACTION_DOWN ) {
            mStartTime = event.eventTime;
        }

        if ((event?.eventTime?.minus(mStartTime)!! >= ViewConfiguration.getTapTimeout()) && event.action == MotionEvent.ACTION_MOVE) {
            return super.onInterceptTouchEvent(event)
        }

        return false;
    }
}