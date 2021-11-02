package com.gcode.tools.internal.annotation

/**
 * Indicates that the feature may be removed in the future
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
internal annotation class UnderTest()