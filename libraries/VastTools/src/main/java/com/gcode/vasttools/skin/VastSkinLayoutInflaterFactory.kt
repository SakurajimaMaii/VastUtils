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

package com.gcode.vasttools.skin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.gcode.vasttools.skin.utils.VastSkinUtils
import java.lang.reflect.Constructor
import java.util.*
import kotlin.collections.HashMap

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/3/27 18:35

class VastSkinLayoutInflaterFactory(private val activity: Activity) : LayoutInflater.Factory2,
    Observer {

    private val skinAttribute: VastSkinAttribute =
        VastSkinAttribute()

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

        var view = createSDKView(name, context, attrs)
        if (null == view) {
            view = createView(name, context, attrs)
        }

        if (null != view) {
            skinAttribute.look(view, attrs)
        }
        return view

    }

    private fun createSDKView(name: String, context: Context, attrs: AttributeSet): View? {

        if (-1 != name.indexOf('.')) {
            return null
        }

        for (i in mClassPrefixList.indices) {
            val view = createView(mClassPrefixList[i] + name, context, attrs)
            if (view != null) {
                return view
            }
        }
        return null
    }

    private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
        val constructor = findConstructor(context, name)
        try {
            return constructor!!.newInstance(context, attrs)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun findConstructor(context: Context, name: String): Constructor<out View>? {
        var constructor = mConstructorMap[name]
        if (constructor == null) {
            try {
                val clazz = context.classLoader.loadClass(name).asSubclass(
                    View::class.java
                )
                constructor = clazz.getConstructor(*mConstructorSignature)
                mConstructorMap[name] = constructor
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return constructor
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }

    override fun update(o: Observable, arg: Any) {
        VastSkinUtils.updateStatusBarColor(activity)
        skinAttribute.applySkin()
    }

    companion object {

        private val mClassPrefixList = arrayOf(
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
        )

        private val mConstructorSignature = arrayOf(
            Context::class.java, AttributeSet::class.java
        )

        private val mConstructorMap = HashMap<String, Constructor<out View>?>()

    }
}