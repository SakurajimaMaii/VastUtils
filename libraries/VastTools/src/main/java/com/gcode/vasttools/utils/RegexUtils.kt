/*
 * MIT License
 *
 * Copyright (c) 2021 码上夏雨
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:JvmName("RegexUtils")

package com.gcode.vasttools.utils

import androidx.annotation.IntRange

/**
 * @Author: Vast Gui
 * @Email: guihy2019@gmail.com
 * @Date: 2022/3/10 15:27
 * @Description: Provides regex checks for some common strings.
 * @Documentation:
 */

/**
 * @receiver String to match.
 * @return true if the String is a email address, false otherwise.
 */
fun String.isEmail(): Boolean {
    return Regex("[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+").matches(this)
}

/**
 * @receiver String to match.
 * @param case 0 Password contains at least numbers and letters. 1
 *     Password contains two or more types:numbers,
 *     letters, and characters. 2 Password contains at
 *     least numbers and letters, and can have characters.
 * @param minLength Minimum password length.
 * @param maxLength Maximum password length.
 * @return true if the String is a password, false otherwise.
 */
@JvmOverloads
fun String.isPwd(
    @IntRange(from = 0, to = 2) case: Int = 0,
    @IntRange(from = 0) minLength: Int = 6,
    @IntRange(from = 0) maxLength: Int = 20
): Boolean {
    if (minLength > maxLength) {
        throw IllegalArgumentException("minLength should be less than maxLength")
    }
    if ((this.length < minLength) or (this.length > maxLength)) {
        throw IllegalArgumentException("Password length does not meet requirements")
    }
    return when (case) {
        0 -> Regex("(?![0-9]+\$)(?![a-zA-Z]+\$)[0-9A-Za-z]{$minLength,$maxLength}").matches(this)
        1 -> Regex("(?![0-9]+\$)(?![a-z]+\$)(?![A-Z]+\$)(?!([^(0-9a-zA-Z)])+\$).{$minLength,$maxLength}").matches(
            this
        )
        2 -> Regex("(?=.*([a-zA-Z].*))(?=.*[0-9].*)[a-zA-Z0-9-*/+.~!@#\$%^&*()]{$minLength,$maxLength}").matches(
            this
        )
        else -> Regex("(?![0-9]+\$)(?![a-zA-Z]+\$)[0-9A-Za-z]{$minLength,$maxLength}").matches(this)
    }
}

/**
 * String to match.
 *
 * @return true if the String is a qq number, false otherwise.
 */
fun String.isQQ(): Boolean {
    return Regex("[1-9][0-9]{4,}").matches(this)
}

/**
 * String to match.By default, it is verified according to the Chinese
 * phone number.
 *
 * @param otherCountryPattern Other National Phone Number Formats.
 * @return true if the String is a phone number, false otherwise.
 */
@JvmOverloads
fun String.isPhoneNumber(otherCountryPattern: String? = null): Boolean {
    return if (null != otherCountryPattern) {
        Regex(otherCountryPattern).matches(this)
    } else {
        Regex("1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}").matches(this)
    }
}

/**
 * @return true if the String is a number, false otherwise.
 */
fun String.isNumeric(): Boolean {
    return Regex("[0-9]*").matches(this)
}