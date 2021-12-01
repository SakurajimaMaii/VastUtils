//[VastNetStateLayout](../../../index.md)/[com.gcode.vastnetstatelayout.view](../index.md)/[VastNetStateMgr](index.md)

# VastNetStateMgr

[androidJvm]\
class [VastNetStateMgr](index.md)(**context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

[VastNetStateMgr](index.md) mainly used to manage the view of different network status

Currently mainly supports four view: **EMPTY DATA** , **NET ERROR** , **LOADING ERROR** , **LOADING**

If you want to customize the view, you can use [setEmptyDataView](set-empty-data-view.md) , [setNetErrorView](set-net-error-view.md) , [setLoadingView](set-loading-view.md) , [setLoadingView](set-loading-view.md) , otherwise the default view will be used.

## Constructors

| | |
|---|---|
| [VastNetStateMgr](-vast-net-state-mgr.md) | [androidJvm]<br>fun [VastNetStateMgr](-vast-net-state-mgr.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [setEmptyDataView](set-empty-data-view.md) | [androidJvm]<br>fun [setEmptyDataView](set-empty-data-view.md)(@[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)()emptyDataRetryViewId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set [emptyDataRetryViewId](set-empty-data-view.md) and [emptyDataVs](empty-data-vs.md) |
| [setLoadingErrorView](set-loading-error-view.md) | [androidJvm]<br>fun [setLoadingErrorView](set-loading-error-view.md)(@[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)()loadingErrorRetryViewId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set [loadingErrorRetryViewId](set-loading-error-view.md) and [loadingErrorVs](loading-error-vs.md) |
| [setLoadingView](set-loading-view.md) | [androidJvm]<br>fun [setLoadingView](set-loading-view.md)(@[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)()loadingViewId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set [loadingViewId](set-loading-view.md) and [loadingVs](loading-vs.md) |
| [setNetErrorView](set-net-error-view.md) | [androidJvm]<br>fun [setNetErrorView](set-net-error-view.md)(@[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)()netErrorRetryViewId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Set [netErrorRetryViewId](set-net-error-view.md) and [netErrorRetryVs](net-error-retry-vs.md) |
| [setNetWorkListener](set-net-work-listener.md) | [androidJvm]<br>fun [setNetWorkListener](set-net-work-listener.md)(vastNetWorkClickListener: [VastNetWorkClickListener](../../com.gcode.vastnetstatelayout.interfaces/-vast-net-work-click-listener/index.md))<br>You can set the view click event including the following status:**NET ERROR** |
| [setVastRetryClickListener](set-vast-retry-click-listener.md) | [androidJvm]<br>fun [setVastRetryClickListener](set-vast-retry-click-listener.md)(vastRetryClickListener: [VastRetryClickListener](../../com.gcode.vastnetstatelayout.interfaces/-vast-retry-click-listener/index.md))<br>You can set the view click event including the following status: **EMPTY DATA** , **LOADING ERROR** , **LOADING** |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [emptyDataRetryViewId](empty-data-retry-view-id.md) | [androidJvm]<br>var [emptyDataRetryViewId](empty-data-retry-view-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [emptyDataVs](empty-data-vs.md) | [androidJvm]<br>internal lateinit var [emptyDataVs](empty-data-vs.md): [ViewStub](https://developer.android.com/reference/kotlin/android/view/ViewStub.html) |
| [loadingErrorRetryViewId](loading-error-retry-view-id.md) | [androidJvm]<br>var [loadingErrorRetryViewId](loading-error-retry-view-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [loadingErrorVs](loading-error-vs.md) | [androidJvm]<br>internal lateinit var [loadingErrorVs](loading-error-vs.md): [ViewStub](https://developer.android.com/reference/kotlin/android/view/ViewStub.html) |
| [loadingViewId](loading-view-id.md) | [androidJvm]<br>var [loadingViewId](loading-view-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [loadingVs](loading-vs.md) | [androidJvm]<br>internal lateinit var [loadingVs](loading-vs.md): [ViewStub](https://developer.android.com/reference/kotlin/android/view/ViewStub.html) |
| [netErrorRetryViewId](net-error-retry-view-id.md) | [androidJvm]<br>var [netErrorRetryViewId](net-error-retry-view-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [netErrorRetryVs](net-error-retry-vs.md) | [androidJvm]<br>internal lateinit var [netErrorRetryVs](net-error-retry-vs.md): [ViewStub](https://developer.android.com/reference/kotlin/android/view/ViewStub.html) |
| [vastNetWorkClickListener](vast-net-work-click-listener.md) | [androidJvm]<br>internal var [vastNetWorkClickListener](vast-net-work-click-listener.md): [VastNetWorkClickListener](../../com.gcode.vastnetstatelayout.interfaces/-vast-net-work-click-listener/index.md)? = null<br>It will be called when you want to set net work. |
| [vastRetryClickListener](vast-retry-click-listener.md) | [androidJvm]<br>internal var [vastRetryClickListener](vast-retry-click-listener.md): [VastRetryClickListener](../../com.gcode.vastnetstatelayout.interfaces/-vast-retry-click-listener/index.md)? = null<br>It will be called when you want to set retry click event. |
