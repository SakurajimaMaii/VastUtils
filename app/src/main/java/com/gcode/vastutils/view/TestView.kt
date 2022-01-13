package com.gcode.vastutils.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/8
 */
class TestView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle:Int = 0): HorizontalScrollView(context,attrs, defStyle) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                Log.i("Hello","TestView onTouchEvent MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE->{
                Log.i("Hello","TestView onTouchEvent MotionEvent.ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.i("Hello","TestView onTouchEvent MotionEvent.ACTION_UP")
            }
        }
        return super.onTouchEvent(event)
    }
}