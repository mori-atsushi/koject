@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject

import com.moriatsushi.koject.internal.Identifier
import kotlin.test.Test
import kotlin.test.assertEquals

class MergedExtrasTest {
    @Test
    fun mergeValues() {
        val extras1 = buildExtras {
            provides { "String" }
        }
        val extras2 = buildExtras {
            provides { 100 }
        }
        val merged = extras1 merge extras2

        val actual1 = merged.getOrNull<String>(Identifier.of<String>())
        assertEquals("String", actual1)

        val actual2 = merged.getOrNull<Int>(Identifier.of<Int>())
        assertEquals(100, actual2)
    }

    @Test
    fun overrideValues() {
        val extras1 = buildExtras {
            provides { "String" }
        }
        val extras2 = buildExtras {
            provides { "Override" }
        }
        val merged = extras1 merge extras2

        val actual = merged.getOrNull<String>(Identifier.of<String>())
        assertEquals("Override", actual)
    }
}
