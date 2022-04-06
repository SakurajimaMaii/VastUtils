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

package com.gcode.vastutilsjava.basebindadpexample.model;

import androidx.annotation.Nullable;

import com.gcode.vastadapter.interfaces.VAapClickEventListener;
import com.gcode.vastadapter.interfaces.VAdpLongClickEventListener;
import com.gcode.vastadapter.interfaces.VastBindAdapterItem;
import com.gcode.vastutilsjava.R;

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/31 13:57
 * @Description:
 * @Documentation:
 */
public class Picture implements VastBindAdapterItem {

    private int drawable;
    private VAapClickEventListener clickEventListener;
    private VAdpLongClickEventListener longClickEventListener;

    public Picture(int drawable, VAapClickEventListener clickEventListener, VAdpLongClickEventListener longClickEventListener) {
        this.drawable = drawable;
        this.clickEventListener = clickEventListener;
        this.longClickEventListener = longClickEventListener;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    @Override
    public int getVBAdpItemType() {
        return R.layout.item_bind_imageview;
    }

    @Override
    public void setVBAapClickEventListener(@Nullable VAapClickEventListener l) {
        clickEventListener = l;
    }

    @Nullable
    @Override
    public VAapClickEventListener getVBAapClickEventListener() {
        return clickEventListener;
    }

    @Override
    public void setVBAdpLongClickEventListener(@Nullable VAdpLongClickEventListener l) {
        longClickEventListener = l;
    }

    @Nullable
    @Override
    public VAdpLongClickEventListener getVBAdpLongClickEventListener() {
        return longClickEventListener;
    }
}