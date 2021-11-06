package com.gcode.vasttools.internal.annotation

/**
 * Indicates that the feature may be removed or revise in the future.
 */
@Target(
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.BINARY)
internal annotation class UnderTest()