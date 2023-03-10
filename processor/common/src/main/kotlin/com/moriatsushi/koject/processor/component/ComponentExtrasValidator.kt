package com.moriatsushi.koject.processor.component

import com.moriatsushi.koject.processor.error.CodeGenerationException
import com.moriatsushi.koject.processor.symbol.ComponentExtrasDeclaration

internal class ComponentExtrasValidator {
    fun validate(extrasDeclaration: ComponentExtrasDeclaration) {
        extrasDeclaration.extras.extras.forEach {
            if (it.isSingleton) {
                throw CodeGenerationException(
                    "${it.location.value}: Component extras types cannot be a singleton.",
                )
            }
        }
    }
}
