package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.KSName

data class DummyKSName(
    private val string: String = "",
    private val qualifier: String = "",
    private val shortName: String = "",
) : KSName {
    companion object {
        val rootPackageName = DummyKSName(string = "com.package")
    }

    override fun asString(): String {
        return string
    }

    override fun getQualifier(): String {
        return qualifier
    }

    override fun getShortName(): String {
        return shortName
    }
}
