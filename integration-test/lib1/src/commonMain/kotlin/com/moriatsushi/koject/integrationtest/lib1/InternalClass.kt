package com.moriatsushi.koject.integrationtest.lib1

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.inject
import kotlin.test.assertIs

@Provides
internal class InternalClass1

@Provides
internal class InternalClass2(
    val internalClass1: InternalClass1,
)

@Provides
internal class InternalClass3(
    val internalClass1: InternalClass1,
    val libClass1: LibClass1,
)

fun checkInternalClassInject() {
    val internalClass1 = inject<InternalClass1>()
    assertIs<InternalClass1>(internalClass1)

    val internalClass2 = inject<InternalClass2>()
    assertIs<InternalClass2>(internalClass2)

    val internalClass3 = inject<InternalClass3>()
    assertIs<InternalClass3>(internalClass3)
}
