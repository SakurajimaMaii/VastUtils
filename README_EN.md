# [ToolsForAndroid](https://github.com/SakurajimaMaii/ToolsForAndroid)

[简体中文](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/README.md) | English

## 🚀 How to

### VastTools

```gradle
implementation 'io.github.sakurajimamaii:VastTools:0.0.2'
```

### VastAdapter

```gradle
implementation 'io.github.sakurajimamaii:VastAdapter:0.0.1'
```

## 👍 Start quickly

Currently, the project is mainly provided `VastAdapter` and `VastTools`

### VastAdapter

🤔According to the needs of the project, I design Adapter into two modes, respectively:

- support DataBinding
- not support DataBinding

If your project supports databinding, please use **BaseGcodeBindingAdapter**, if does not support , please use **BaseGcodeAdapter**😉

#### Instructions for use

- [BaseGcodeBindingAdapter](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeBindingAdapter.md)
- [BaseGcodeAdapter](https://github.com/SakurajimaMaii/ToolsForAndroid/blob/master/docs/BaseGcodeAdapter.md)

#### Methods

|                                 Method name                                 |                                      illustrate                                      |
| :-------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: |
|                               getItemCount()                                |                            Returns the size of the item.                             |
|                       getItemViewType(position: Int)                        |                          Get ViewType according to Position                          |
|                                isItemEmpty()                                | Returns `true` if the collection is empty (contains no elements), `false` otherwise. |
|                           getItemByPos(pos: Int)                            |                              Returns item by `position`                              |
|                             addItem(item: obj?)                             |                   Adds the specified item to the end of this list                    |
|                      addItemByPos(item: obj, pos: Int)                      |                      Adds the specified item by the `position`                       |
|             addItemsByPos(addItems: MutableList<obj>, pos: Int)             |                     Adds List of Item by `position` to the list                      |
|                         removeItemByObj(item: obj?)                         |                                Remove Item by object                                 |
|                          removeItemByPos(pos: Int)                          |                             Remove objects by `position`                             |
| removeItemsByPos(startPos: Int, endPos: Int,includeEndPos: Boolean = false) |                       Remove item from `startPos` to `endPos`                        |
|                                 clearItem()                                 |                                     Empty items                                      |

### VastTools

#### MsgWindowUtils

Message pop-up tool class, currently support three messages pop-ups `Short Toast` `Long Toast` `showDlgMsg`

```kotlin
MsgWindowUtils.showShortMsg(this, "These permissions are denied: $deniedList")
```

#### ScreenSizeUtils

Used to return the screen size related information, so that you can design control size according to this

```kotlin
fun isAllScreenDevice(context: Context)
fun getMobileScreenWidth(context: Context)
fun getMobileScreenHeight(context: Context)
```

#### LogUtils

The log tool class is mainly used for printing the log, the content contains `Class name` `the number of rows of log call` `the method of log call` `keyboard` `Out information`

![log example](https://img-blog.csdnimg.cn/e5e2c730d428481fba80a41f8c126af6.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA56CB5LiK5aSP6Zuo,size_20,color_FFFFFF,t_70,g_se,x_16)

**1.Log on and off**

```kotlin
LogUtils.setLogEnabled(false)
```

**2.Use**

```kotlin
LogUtils.i(this.javaClass,"Hello","${1+2}")
LogUtils.d(this.javaClass,"Hello","${1+2}")
LogUtils.e(this.javaClass,"Hello","${1+2}")
LogUtils.v(this.javaClass,"Hello","${1+2}")
LogUtils.w(this.javaClass,"Hello","${1+2}")
```

**3.Custom output content**

From the implementation `Logcontent` interface from defined output log content

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

The camera tool class is mainly used to get pictures in the album and convert it to `bitmap` object

```kotlin
// Getted picture
private var bitmap: Bitmap? = null

private val getPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
    if(result.resultCode == Activity.RESULT_OK){
        bitmap = result.data?.let { CameraUtils.displayImage(it,this) }
    }
}
```

#### AppUtils

App tool class is used to get app name, package name, icon, version number basic information

```kotlin
AppUtils.getAppName(this)
AppUtils.getPackageName(this)
AppUtils.getVersionName(this)
AppUtils.getVersionCode(this)
AppUtils.getBitmap(this)
```

#### DateUtils

Used to obtain date related information

#### DensityUtils

Used for dimensional conversion, currently provided four methods

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

## Reference source

[Android Get pictures: taking photos and from albums](https://www.jianshu.com/p/57487bb1ec5a)

[Android gets the app name, package name, icon, version number basic information](https://blog.csdn.net/jia635/article/details/78722073)
