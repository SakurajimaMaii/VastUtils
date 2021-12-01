//[VastAdapter](../../../index.md)/[com.gcode.vastadapter](../index.md)/[BaseVastBindingAdapter](index.md)/[setVariableId](set-variable-id.md)

# setVariableId

[androidJvm]\
abstract fun [setVariableId](set-variable-id.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Set VariableId value For example, in the layout file <data>     <variable     name="item"     type="com.gcode.gutilssampledemo.Person" /> </data>

Then the implementation of the function should be override fun setVariableId(): Int {     return BR.item } For more information, please refer to the example given in the link below https://github.com/SakurajimaMaii/GStyleUtils

#### Return

Int
