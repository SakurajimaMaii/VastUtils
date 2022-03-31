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

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import com.gcode.vasttools.skin.utils.VastSkinUtils
import java.util.*

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/27 19:45
 * @Description:
 * @Documentation:
 */
internal class VastSkinActivityLifecycle constructor(private val mObservable: Observable) :
    Application.ActivityLifecycleCallbacks {
    private val mLayoutInflaterFactories: ArrayMap<Activity, VastSkinLayoutInflaterFactory> =
        ArrayMap()

    @SuppressLint("DiscouragedPrivateApi")
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        VastSkinUtils.updateStatusBarColor(activity)
        val layoutInflater = activity.layoutInflater

        val skinLayoutInflaterFactory:VastSkinLayoutInflaterFactory
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
            try {
                // Set the mFactorySet with false
                val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
                field.isAccessible = true
                field.setBoolean(layoutInflater, false)
                skinLayoutInflaterFactory = VastSkinLayoutInflaterFactory(activity)
                LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory)
                mLayoutInflaterFactories[activity] = skinLayoutInflaterFactory
                mObservable.addObserver(skinLayoutInflaterFactory)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            try {
                skinLayoutInflaterFactory = forceSetFactory2(layoutInflater,activity)
                mLayoutInflaterFactories[activity] = skinLayoutInflaterFactory
                mObservable.addObserver(skinLayoutInflaterFactory)
            }catch (e:IllegalAccessException){
                e.printStackTrace()
            }catch (e:NoSuchFieldException){
                e.printStackTrace()
            }
        }
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        val observer: VastSkinLayoutInflaterFactory? = mLayoutInflaterFactories.remove(activity)
        VastSkinManager.deleteObserver(observer)
    }

    // See https://blog.csdn.net/qq_25412055/article/details/100033637
    @SuppressLint("DiscouragedPrivateApi")
    @Throws(IllegalAccessException::class,NoSuchFieldException::class)
    private fun forceSetFactory2(
        inflater: LayoutInflater,
        activity: Activity
    ): VastSkinLayoutInflaterFactory {
        val compatClass = LayoutInflaterCompat::class.java
        val inflaterClass = LayoutInflater::class.java
        val sCheckedField = compatClass.getDeclaredField("sCheckedField")
        sCheckedField.isAccessible = true
        sCheckedField.setBoolean(inflater, false)
        val mFactory = inflaterClass.getDeclaredField("mFactory")
        mFactory.isAccessible = true
        val mFactory2 = inflaterClass.getDeclaredField("mFactory2")
        mFactory2.isAccessible = true
        val factory = VastSkinLayoutInflaterFactory(activity)
        mFactory2[inflater] = factory
        mFactory[inflater] = factory
        return factory
    }

}