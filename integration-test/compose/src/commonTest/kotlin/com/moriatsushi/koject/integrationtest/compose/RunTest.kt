package com.moriatsushi.koject.integrationtest.compose

import com.moriatsushi.koject.Koject

expect fun Koject.runTest(
    block: () -> Unit,
)
