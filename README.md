# [ToolsForAndroid](https://github.com/SakurajimaMaii/ToolsForAndroid)

简体中文 | [English](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/README_EN.md)

# ⚠️
为了之后的项目维护，我已将项目向 `MavenCentral()` 迁移，并且会将 `Adapter` 和 `Tools` 拆开作为单独的module进行维护，如果你使用**2.6.0之前的版本(包含2.6.0)**，依赖项的声明如下：

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

从**2.6.1开始**，**2.6.1**作为项目的版号，而**Adapter**和**Tools**作为单独的Module以**0.0.1**作为起始版本号，添加引用如下：

```gradle
implementation 'io.github.sakurajimamaii:VastTools:0.0.1'
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

#### [MsgWindowUtils](https://github.com/SakurajimaMaii/ToolsForAndroid/wiki/MsgWindowUtils)

弹窗工具类，点击标题查看**文档**

#### [ScreenSizeUtils](https://github.com/SakurajimaMaii/ToolsForAndroid/wiki/ScreenSizeUtils)

用于返回屏幕大小相关信息，方便你根据此来设计控件尺寸，点击标题查看**文档**

#### [LogUtils](https://github.com/SakurajimaMaii/ToolsForAndroid/wiki/LogUtils)

日志工具类主要用于打印日志，打印的内容包含 `类名`  `Log调用的行数` `调用Log的方法` `关键字` `输出信息`，点击标题查看**文档**

![log example](https://img-blog.csdnimg.cn/e5e2c730d428481fba80a41f8c126af6.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA56CB5LiK5aSP6Zuo,size_20,color_FFFFFF,t_70,g_se,x_16)

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

#### [AppUtils](https://github.com/SakurajimaMaii/ToolsForAndroid/wiki/AppUtils)

App工具类用来获取APP的应用程序名称、包名、图标，版本号基本信息，点击标题查看**文档**

#### DateUtils

用于获取日期相关信息

#### [DensityUtils](https://github.com/SakurajimaMaii/ToolsForAndroid/wiki/DensityUtils)

用于尺寸转换，点击标题查看**文档**

## 参考来源

[Android获取图片：拍照和从相册中选择](https://www.jianshu.com/p/57487bb1ec5a)

[Android获取APP的应用程序名称、包名、图标，版本号基本信息](https://blog.csdn.net/jia635/article/details/78722073)
