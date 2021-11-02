package com.gcode.vasttools.internal.annotation

/**
 * You should make sure that your app has this permission
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
internal annotation class CheckPermission(val permission: String)