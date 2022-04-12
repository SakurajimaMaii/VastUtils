/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gcode.vastutils.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/1/8
 */
class TestLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle:Int = 0): LinearLayout(context,attrs, defStyle) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                Log.i("Hello","TestLayout onInterceptTouchEvent MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE->{
                Log.i("Hello","TestLayout onInterceptTouchEvent MotionEvent.ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.i("Hello","TestLayout onInterceptTouchEvent MotionEvent.ACTION_UP")
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                Log.i("Hello","TestLayout onTouchEvent MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE->{
                Log.i("Hello","TestLayout onTouchEvent MotionEvent.ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.i("Hello","TestLayout onTouchEvent MotionEvent.ACTION_UP")
            }
        }
        return super.onTouchEvent(event)
    }
}