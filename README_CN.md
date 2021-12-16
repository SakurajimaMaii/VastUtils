<h1 align="center">VastUtils</h1>

<p align="center">简体中文 | <a href="https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/README.md">English</a></p>

### VastTools

包含了我们常用的一些必要功能

💫 特性

- 👍 AppInfoUtils 支持获取`应用程序名` `当前版本名` `应用版本号` `应用包名` `应用图标` `应用Debug状态`
- 👍 DateUtils 支持获取日期等相关信息，详情可以[点击这里](https://juejin.cn/post/7029336437493350407)
- 👍 DensityUtils 提供了dp px sp之间的相互转换，同时也支持`56f.sp`
- 👍 IntentUtils 提供了常用的Intent，例如`拨打电话` `打开网页`
- 👍 LogUtils 提供了日志打印工具，详情可以[点击这里](https://juejin.cn/post/7027420579607248932)
- 👍 MergeBitmapUtils 提供了BitMap合并
- 👍 MsgWindowUtils 提供了三种常用消息提示框`Short Toast` `Long Toast` `Dialog`
- 👍 ScreenSizeUtils 提供了屏幕尺寸获取方法，屏幕长度，屏幕宽度，是否是全面屏
- 👍 SystemUtils 提供了获取系统信息的方法

😁 使用
```gradle
implementation 'io.github.sakurajimamaii:VastTools:0.0.4'
```

### VastAdapter
帮助你快速的构建适合`RecyclerView`的`Adapter`,详情可以[点击这里](https://juejin.cn/post/7020284564270481439)，以下为示例：
```java
public class LocalMusicAdapter extends BaseVastBindingAdapter<LocalMusicBean> {
    public LocalMusicAdapter(@NonNull List<LocalMusicBean> items) {
        super(items);
    }

    @Override
    public int setVariableId() {
        return BR.item;
    }
}
```
对你没看错，就是如此简单。

😁 使用
```gradle
implementation 'io.github.sakurajimamaii:VastAdapter:0.0.2'
```

### VastNetStateLayout
VastNatStateLayout继承自framelayout。你可以自定义下列状态页面: loading,error,ok,empty data。详情可以[点击这里](https://juejin.cn/post/7040032577830256653),以下为演示图：
<div align="center">
	<image src="https://img-blog.csdnimg.cn/07db693ac8154e968cc7dbbd8f95ef3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/a270b3dd21554189a07ac50e3a426c6f.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/6925a02f78cd46e2904bf524b74d1c3e.jpg" width="30%"/>
	<image src="https://img-blog.csdnimg.cn/e3d521f969014a2387c8ff4348c47800.jpg" width="30%"/>
</div>

😁 使用
```gradle
implementation 'io.github.sakurajimamaii:VastNetStateLayout:0.0.2'
```

### VastSwipeListView
一款支持自定义的仿QQ列表滑动控件，详情可以[点击这里](https://juejin.cn/post/7037127443244646431),以下为演示图：
<div align="center"><img src="https://img-blog.csdnimg.cn/08c1d95b98af4f089342ab9163d0cd23.gif" width=30%></div>

😁 使用
```gradle
implementation 'io.github.sakurajimamaii:VastSwipeListView:0.0.1'
```