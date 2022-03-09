package com.gcode.vasttools.model.bean

/**
 * @OriginalAuthor: Vast Gui
 * @OriginalDate:
 * @EditAuthor: Vast Gui
 * @EditDate: 2022/2/18
 */

// Response json bean
open class Response<T:Any> (val code:Int,val msg:String,val data:T)