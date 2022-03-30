package com.gcode.vastutils.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gcode.vastutils.databinding.ActivityTestBinding;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding mBinding;

    private static final Map<String,Constructor<? extends View>> mConstructorMap = new HashMap<>();

    private static final Class<?>[] mConstructorSignature = new Class[]{
            Context.class, AttributeSet.class
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

}