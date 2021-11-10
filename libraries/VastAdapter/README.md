# VastAdapter

🤔According to the needs of the project, I design Adapter into two modes, respectively:

- support DataBinding
- not support DataBinding

If your project supports databinding, please use **BaseVastBindingAdapter**, if does not support , please use **BaseVastAdapter**😉

## BaseVastBindingAdapter

1. First let the object extends the `BaseVastItem` interface, and use the layout id as the return value of `getItemViewType`, for example

```kotlin
data class Person(val firstName:String,val lastName: String):BaseVastItem{
    override fun getItemViewType(): Int {
        return R.layout.item_layout
    }

    override fun getItemBindViewType(): Int {

    }
}
```

2. Use in xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/util_gold">
    <TextView
        android:id="@+id/firstName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <TextView
        android:id="@+id/lastName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

3. Create your adapter extends `BaseVastAdapter` and override the `bindData` method.

```kotlin
class TestBaseAdapter(items: MutableList<Person>) : BaseVastAdapter<Person>(items) {
        override fun bindData(holder: RecyclerViewHolder, position: Int, item: Person) {
        holder.findViewById<TextView>(R.id.firstName).text = item.firstName
        holder.findViewById<TextView>(R.id.lastName).text = item.lastName
    }
}
```

## BaseVastBindingAdapter


1. First let the object extends the `BaseVastItem` interface, and use the layout id as the return value of `getItemBindViewType`, for example

```kotlin
data class Person(val firstName:String,val lastName: String):BaseVastItem{
    override fun getItemViewType(): Int {

    }

    override fun getItemBindViewType(): Int {
        return R.layout.item_bind_layout
    }
}
```

2. Use in xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <variable
            name="item"
            type="com.gcode.vastutils.Person" />
    </data>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical" android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{item.firstName}"/>
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{item.lastName}"/>
    </LinearLayout>
</layout>
```

3. Create your adapter extends `BaseVastBindingAdapter` and override the `setVariableId` method.

```kotlin
class TestBaseBindingAdapter(items: MutableList<BaseItem>) :BaseVastBindingAdapter(items){
    override fun setVariableId(): Int {
        return BR.item
    }
}
```

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