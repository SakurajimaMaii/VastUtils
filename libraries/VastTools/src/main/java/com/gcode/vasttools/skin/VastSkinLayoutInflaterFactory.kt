/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

internal class VastSkinLayoutInflaterFactory(private val activity: Activity) : LayoutInflater.Factory2,
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