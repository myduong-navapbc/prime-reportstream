package gov.cdc.prime.reportstream.shared

import org.apache.commons.lang3.StringUtils
import java.util.Base64

/**
 * A collection of string utilities
 */
object StringUtilities {
    /**
     * A wrapper around the Apache trimToNull function that allows us to call it
     * as an extension method. This is just more of a convenience feature, but it
     * was suggested as part of the DateUtilities changes.
     *
     * If the string value is null, it returns null. If the string value is blank
     * or empty string, it converts it to null, otherwise it trims trailing whitespace.
     */
    fun String?.trimToNull(): String? {
        if (this == null) return null

        return StringUtils.trimToNull(this)
    }

    /**
     * A wrapper around toIntOrNull
     * utility to improve readability when parsing params
     *
     * usage:
     * request.queryParameters['foo']?.toIntOrDefault(30)
     */
    fun String?.toIntOrDefault(default: Int = 0): Int = this?.toIntOrNull() ?: default

    /**
     * Trim and truncate the string to the [maxLength] preserving as much of the non-whitespace as possible
     */
    fun String.trimAndTruncate(maxLength: Int?): String {
        val startTrimmed = this.trimStart()
        val truncated =
            if (maxLength != null && startTrimmed.length > maxLength) {
                startTrimmed.take(maxLength)
            } else {
                startTrimmed
            }
        return truncated.trimEnd()
    }

    /**
     * Handy extension functions for base 64 encoding/decoding
     */
    fun String.base64Encode(): String = Base64.getEncoder().encodeToString(this.toByteArray(Charsets.UTF_8))
    fun String.base64Decode(): String = String(Base64.getDecoder().decode(this), Charsets.UTF_8)
}