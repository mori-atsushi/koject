package com.moriatsushi.koject.processor.code

import java.security.MessageDigest
import java.util.Base64

/**
 * Escape a String for use in method names and class names
 */
internal val String.escapedForCode: String
    get() = this
        .replace(" ", "")
        .replace(".", "_")
        .replace("<", "__")
        .replace(">", "__")
        .replace(",", "__")
        .replace("?", "_nullable")
        .replace("`", "")

/**
 * Hash a String for use in method names and class names
 */
internal val String.hashForCode: String
    get() {
        val sha256 = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
        return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(sha256)
            .take(16)
            .replace("-", "_")
    }
