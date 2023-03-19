package com.moriatsushi.koject.processor.debug

import com.google.devtools.ksp.processing.KSPLogger
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

internal class TimeMeasure(
    private val enabled: Boolean,
    private val logger: KSPLogger,
) {
    @OptIn(ExperimentalTime::class)
    inline fun measure(
        label: String,
        crossinline block: () -> Unit,
    ) {
        if (!enabled) {
            block()
            return
        }

        val mark = TimeSource.Monotonic.markNow()
        block()
        val duration = mark.elapsedNow()
        logger.info("$label: $duration")
    }
}
