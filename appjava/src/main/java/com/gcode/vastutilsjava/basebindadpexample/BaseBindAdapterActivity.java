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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gcode.vastadapter.interfaces.VAapClickEventListener;
import com.gcode.vastadapter.interfaces.VastBindAdapterItem;
import com.gcode.vasttools.base.VastVbActivity;
import com.gcode.vasttools.utils.ToastUtils;
import com.gcode.vastutilsjava.R;
import com.gcode.vastutilsjava.basebindadpexample.model.Picture;
import com.gcode.vastutilsjava.databinding.ActivityBaseBindAdapterBinding;

import java.util.ArrayList;

public class BaseBindAdapterActivity extends VastVbActivity<ActivityBaseBindAdapterBinding> {

    private ArrayList<VastBindAdapterItem> datas = new ArrayList<>();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        VAapClickEventListener click = (view, pos) -> {
            ToastUtils.showShortMsg(this,"Hello");
        };

        for (int i = 0; i < 10; i++) {
            datas.add(new Picture(R.drawable.ic_knots, click, null));
        }

        BaseBindAdapter adapter = new BaseBindAdapter(datas);

        adapter.setOnItemClickListener((view, position) -> {
            // Something you want to do
        });
        adapter.setOnItemLongClickListener((view, position) -> {
            // Something you want to do
            return true;
        });

        mBinding.dataRv.setAdapter(adapter);
        mBinding.dataRv.setLayoutManager(new LinearLayoutManager(this));
    }

}