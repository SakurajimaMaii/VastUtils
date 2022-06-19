/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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