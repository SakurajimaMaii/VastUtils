package com.gcode.tools.internal.annotation

/**
 * Indicates that the feature may be removed in the future
 */
@kotlin.annotation.Target(AnnotationTarget.PROPERTY)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
internal annotation class UnderTest()