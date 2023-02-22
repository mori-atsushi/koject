package com.moriatsushi.koject.internal.identifier

data class TypeStruct(
    val name: String,
    val isNullable: Boolean = false,
    val arguments: List<TypeStruct> = emptyList(),
) {
    override fun toString(): String {
        return buildString {
            append(name)
            if (arguments.isNotEmpty()) {
                append("<")
                arguments.joinTo(this, ", ") {
                    it.toString()
                }
                append(">")
            }
            if (isNullable) {
                append("?")
            }
        }
    }

    companion object
}
