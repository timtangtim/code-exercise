package com.projectx.codeexercise

import android.widget.TextView
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit view_weather_detail, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTimezoneCovert() {
        assertEquals(calTimezone(28800), 8)
    }
}