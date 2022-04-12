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

package com.gcode.vastutilsjava.basebindadpexample;

import android.content.Context;

import androidx.annotation.NonNull;

import com.gcode.vastadapter.base.VastBindAdapter;
import com.gcode.vastadapter.interfaces.VastBindAdapterItem;
import com.gcode.vastutilsjava.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/31 14:11
 * @Description:
 * @Documentation:
 */
public class BaseBindAdapter extends VastBindAdapter {

    private ArrayList<VastBindAdapterItem> datas;
    private Context mContext;

    public BaseBindAdapter(@NonNull List<VastBindAdapterItem> dataSource,@NonNull Context mContext) {
        super(dataSource,mContext);
        datas.addAll(dataSource);
    }

    @Override
    public int setVariableId() {
        return BR.item;
    }

    public Boolean isEmpty(){
        return datas.isEmpty();
    }

}