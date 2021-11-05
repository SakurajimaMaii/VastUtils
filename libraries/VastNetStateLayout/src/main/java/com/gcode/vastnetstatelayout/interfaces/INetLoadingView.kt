package com.gcode.vastnetstatelayout.interfaces;

import android.content.Context;
import android.view.View;

/**
 * INetLoadingView is a interface for  loading.
 * You can implement it to customize the UI.
 * SimpleNetLoadingView has implemented it <p></p>
 *
 * Created by Vast Gui on 2021/11/5
 */
interface INetLoadingView {
    fun getView(context:Context):View

    fun hide();

    fun show();
}
