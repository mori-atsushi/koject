@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject

import com.moriatsushi.koject.internal.Identifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

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

    @Test
    fun empty() {
        val empty = Extras.empty
        val extras = buildExtras { provides { "String" } }
        val merged1 = extras merge empty
        val merged2 = empty merge extras
        val merged3 = empty merge empty

        assertSame(extras, merged1)
        assertSame(extras, merged2)
        assertSame(empty, merged3)
    }
}
