# [ToolsForAndroid](https://github.com/SakurajimaMaii/ToolsForAndroid)

简体中文 | [English](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/README_EN.md)

| 最后更新时间 | 稳定版本 | 下一个版本 | 测试版 |
| :----------: | :------: | :--------: | :----: |
|  2021/10/28  |  2.6.0   |   2.7.0    |        |

## 声明依赖项

在项目根目录下的 `build.gradle` 添加

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

添加依赖

```gradle
dependencies {
  implementation 'com.github.SakurajimaMaii:ToolsForAndroid:2.5.0'
}
```

## 快速开始

目前该项目主要提供 `Adapter` 和 `Utils` 两类工具

### Adapter

根据项目的需求，我将 Adapter 设计成两种模式，分别是：

- 支持 DataBinding
- 不支持 DataBinding

如果你的项目**支持 DataBinding**，请使用**BaseGcodeBindingAdapter**，如果**不支持 DataBinding**，请使用**BaseGcodeAdapter**

#### 使用说明

- [BaseGcodeBindingAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeBindingAdapter.md)
- [BaseGcodeAdapter 使用](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeAdapter.md)

#### Adapter 方法介绍

adapter 目前提供了以下几种方法

|                                   方法名                                    |             说明             |
| :-------------------------------------------------------------------------: | :--------------------------: |
|                               getItemCount()                                |       获取 item 的数量       |
|                       getItemViewType(position: Int)                        | 根据 position 获取 ViewType  |
|                                isItemEmpty()                                |     判断 items 是否为空      |
|                           getItemByPos(pos: Int)                            |      根据 pos 获取 item      |
|                             addItem(item: obj?)                             |       在数据集最后添加       |
|                             addItem(item: obj?)                             |     在最后添加对象 item      |
|                      addItemByPos(item: obj, pos: Int)                      |    根据 pos 添加对象 item    |
|             addItemsByPos(addItems: MutableList<obj>, pos: Int)             |   通过 pos 来批量添加 item   |
|                         removeItemByObj(item: obj?)                         |     通过对象来删除 Item      |
|                          removeItemByPos(pos: Int)                          |     通过 pos 来删除对象      |
| removeItemsByPos(startPos: Int, endPos: Int,includeEndPos: Boolean = false) | startPos到endPos范围内的元素 |
|                                 clearItem()                                 |          清空 items          |

### Utils 介绍

#### MsgWindowUtils

消息弹窗工具类，目前支持三种消息弹窗 `Short Toast` `Long Toast` `showDlgMsg`

```kotlin
MsgWindowUtils.showShortMsg(this, "These permissions are denied: $deniedList")
```

#### ScreenSizeUtils

用于返回屏幕大小相关信息，方便你根据此来设计控件尺寸

```kotlin
fun isAllScreenDevice(context: Context) //判断手机是否为全面屏
fun getMobileScreenWidth(context: Context) //获取屏幕宽度
fun getMobileScreenHeight(context: Context) //获取屏幕高度
```

#### LogUtils

日志工具类主要用于打印日志，打印的内容包含 `类名`  `Log调用的行数` `调用Log的方法` `关键字` `输出信息`

![log example](https://img-blog.csdnimg.cn/e5e2c730d428481fba80a41f8c126af6.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA56CB5LiK5aSP6Zuo,size_20,color_FFFFFF,t_70,g_se,x_16)



**1.日志的开启与关闭**

```kotlin
LogUtils.setLogEnabled(false)
```

**2.调用示例**

```kotlin
LogUtils.i(this.javaClass,"Hello","${1+2}")
LogUtils.d(this.javaClass,"Hello","${1+2}")
LogUtils.e(this.javaClass,"Hello","${1+2}")
LogUtils.v(this.javaClass,"Hello","${1+2}")
LogUtils.w(this.javaClass,"Hello","${1+2}")
```

**3.自定义输出内容**

通过实现 `LogContent` 接口来自定义输出日志内容

```kotlin
LogUtils.setLogContentFormat(object :LogContent{
    override fun logContentFormat(
        methodName: String,
        key: String?,
        content: String?
    ): String {
        return "$key $content"
    }
})
```

#### CameraUtils

相机工具类主要用于获取相册内的图片并将其转化为 `Bitmap` 对象

```kotlin
// 获取到的图片
private var bitmap: Bitmap? = null

// 打开相册
private val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
    if(result.resultCode == Activity.RESULT_OK){
        bitmap = result.data?.let { CameraUtils.displayImage(it,this) }
    }
}
```

#### AppUtils

App工具类用来获取APP的应用程序名称、包名、图标，版本号基本信息

**调用方法**

```kotlin
AppUtils.getAppName(this)
AppUtils.getPackageName(this)
AppUtils.getVersionName(this)
AppUtils.getVersionCode(this)
AppUtils.getBitmap(this)
```

#### DateUtils

用于获取日期相关信息

#### DensityUtils

用于尺寸转换,目前提供了以下四种方法

```kotlin
fun px2dp(pxValue: Float): Float
fun dp2px(dipValue: Float): Float
fun px2sp(pxValue: Float): Float
fun sp2px(spValue: Float): Float

// Convert dp value to float (in pixels)
Float.dp
// Convert sp value to float (in pixels)
Float.sp
```

## 参考来源

[Android获取图片：拍照和从相册中选择](https://www.jianshu.com/p/57487bb1ec5a)

[Android获取APP的应用程序名称、包名、图标，版本号基本信息](https://blog.csdn.net/jia635/article/details/78722073)
