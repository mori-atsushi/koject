package com.moriatsushi.koject.processor.symbol

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

sealed interface ProviderName {
    data class Class(
        val className: ClassName,
    ) : ProviderName

    data class Function(
        val objectName: ClassName? = null,
        val functionName: MemberName,
    ) : ProviderName
}
