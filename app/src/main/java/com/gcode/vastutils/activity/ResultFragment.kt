package com.gcode.vastutils.activity


import android.content.Intent

import android.util.SparseArray
import androidx.fragment.app.Fragment
import java.util.*

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/1
 */
class ResultFragment(): Fragment() {

    private val mCallbacks: SparseArray<com.gcode.vastutils.ActivityLauncher.Callback> =
        SparseArray<com.gcode.vastutils.ActivityLauncher.Callback>()

    private val mCodeGenerator: Random = Random()

    fun newInstance(): ResultFragment {
        return ResultFragment()
    }

    fun startActivityForResult(intent: Intent?, callback: com.gcode.vastutils.ActivityLauncher.Callback?) {
        val requestCode = makeRequestCode()
        mCallbacks.put(requestCode, callback)
        requireActivity().startActivityForResult(intent, requestCode)
    }

    /**
     * @return Randomly generate unique requestCode
     */
    private fun makeRequestCode(): Int {
        var requestCode: Int
        var tryCount = 0
        do {
            requestCode = mCodeGenerator.nextInt(0x0000FFFF)
            tryCount++
        } while (mCallbacks.indexOfKey(requestCode) >= 0 && tryCount < 10)
        return requestCode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbacks[requestCode]?.onActivityResult(resultCode, data)
        mCallbacks.remove(requestCode)
    }
}