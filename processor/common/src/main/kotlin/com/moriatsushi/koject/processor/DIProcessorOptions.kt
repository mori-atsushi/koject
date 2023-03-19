package com.moriatsushi.koject.processor

internal class DIProcessorOptions(
    val measureDuration: Boolean = false,
    val moduleName: String? = null,
) {
    companion object {
        fun of(options: Map<String, String>): DIProcessorOptions {
            return DIProcessorOptions(
                measureDuration = options["measureDuration"].toBoolean(),
                moduleName = options["moduleName"],
            )
        }
    }
}
