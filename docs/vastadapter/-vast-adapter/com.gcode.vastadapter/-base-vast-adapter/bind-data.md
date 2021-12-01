//[VastAdapter](../../../index.md)/[com.gcode.vastadapter](../index.md)/[BaseVastAdapter](index.md)/[bindData](bind-data.md)

# bindData

[androidJvm]\
abstract fun [bindData](bind-data.md)(holder: [BaseVastAdapter.RecyclerViewHolder](-recycler-view-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), item: [obj](index.md))

With the help of the holder.findViewById() , assign values to the children of RecyclerView. E.g holder.findViewById<TextView>(R.id.firstName).text = (item as Person).firstName For more information, please refer to the example given in the link below https://github.com/SakurajimaMaii/GStyleUtils

## Parameters

androidJvm

| | |
|---|---|
| holder | RecyclerViewHolder |
| position | Int |
| item | BaseItem |
