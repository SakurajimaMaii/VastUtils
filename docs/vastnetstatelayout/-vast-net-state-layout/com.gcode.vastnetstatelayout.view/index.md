//[VastNetStateLayout](../../index.md)/[com.gcode.vastnetstatelayout.view](index.md)

# Package com.gcode.vastnetstatelayout.view

## Types

| Name | Summary |
|---|---|
| [VastNetStateLayout](-vast-net-state-layout/index.md) | [androidJvm]<br>class [VastNetStateLayout](-vast-net-state-layout/index.md)@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)()constructor(**context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **attrs**: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)?, **defStyleAttr**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [FrameLayout](https://developer.android.com/reference/kotlin/android/widget/FrameLayout.html)<br>Created by Vast Gui on 2021/11/5[VastNetStateLayout](-vast-net-state-layout/index.md) is a layout to set customized network state ui. |
| [VastNetStateMgr](-vast-net-state-mgr/index.md) | [androidJvm]<br>class [VastNetStateMgr](-vast-net-state-mgr/index.md)(**context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>[VastNetStateMgr](-vast-net-state-mgr/index.md) mainly used to manage the view of different network statusCurrently mainly supports four view: **EMPTY DATA** , **NET ERROR** , **LOADING ERROR** , **LOADING**If you want to customize the view, you can use [setEmptyDataView](-vast-net-state-mgr/set-empty-data-view.md) , [setNetErrorView](-vast-net-state-mgr/set-net-error-view.md) , [setLoadingView](-vast-net-state-mgr/set-loading-view.md) , [setLoadingView](-vast-net-state-mgr/set-loading-view.md) , otherwise the default view will be used. |
