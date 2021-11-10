# VastTools

## MsgWindowUtils

Currently support three messages pop-ups `Short Toast` `Long Toast` `showDlgMsg`

Here is **Kotlin** way

```kotlin
MsgWindowUtils.showShortMsg(this,"VastUtils")
```

Here is **Java** way

```java
MsgWindowUtils.INSTANCE.showDlgMsg(this,"VastUtils");
```

## ScreenSizeUtils

Used to return the screen size information,Including whether the device is a **Full screen**, the **ScreenHeight**, and the **ScreenWidth**.

Here is **Kotlin** way

```kotlin
//in api 31
ScreenSizeUtils.getMobileScreenHeightApi31(this)
//in api 30
ScreenSizeUtils.getMobileScreenHeightApi30(this)
//in api 30 down
ScreenSizeUtils.getMobileScreenHeightApi30Down(this)
```

Here is **Java** way

```java
ScreenSizeUtils.INSTANCE.isAllScreenDeviceApi30(this);
```

## LogUtils

The log tool class is mainly used for printing the log, the content contains `Class name` `the number of rows of log call` `the method of log call` `keyboard` `Out information`

![log example](https://img-blog.csdnimg.cn/e5e2c730d428481fba80a41f8c126af6.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA56CB5LiK5aSP6Zuo,size_20,color_FFFFFF,t_70,g_se,x_16)

**1.Log on and off**

Here is **Kotlin** way

```kotlin
LogUtils.setLogEnabled(false)
```

Here is **Java** way

```java
LogUtils.INSTANCE.setLogEnabled(true);
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

Here is **Kotlin** way

```kotlin
//Use in Kotlin
LogUtils.setLogContentFormat(object :LogContent{
    override fun logContentFormat(methodName: String, key: String?, content: String?): String {
        return super.logContentFormat(methodName, key, content)
    }
})

```

Here is **Java** way

```java
LogUtils.INSTANCE.setLogContentFormat(new LogContent() {
    @NonNull
    @Override
    public String logContentFormat(@NonNull String s, @Nullable String s1, @Nullable String s2) {
        return s1+s2;
    }
});
```

## CameraUtils

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

## AppUtils

App tool class is used to get app name, package name, icon, version number basic information

Here is **Kotlin** way

```kotlin
AppUtils.getAppName(this)
AppUtils.getPackageName(this)
AppUtils.getVersionName(this)
AppUtils.getVersionCode(this)
AppUtils.getBitmap(this)
```

Here is **Java** way

```java
AppUtils.INSTANCE.getAppName(this);
```

## DateUtils

Used to obtain date related information

## DensityUtils

Used for dimensional conversion, currently provided four methods

Here is **Kotlin** way

```kotlin
DensityUtils.dp2px(50f)

// Convert dp value to float (in pixels)
50f.dp
```

Here is **Java** way

```java
DensityUtils.INSTANCE.dp2px(50F);

// Convert dp value to float (in pixels)
DensityUtils.INSTANCE.getDp(50f);
```