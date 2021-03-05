package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    val result = this.trim()
    return if (result.length > len) "${result.substring(0, len).trimEnd()}..." else result
}

fun String.stripHtml() = this.replace(Regex("<.*?>"), "")
        .replace(Regex("&#.*?;"), "")
        .replace("&amp;", "")
        .replace("&lt;", "")
        .replace("&gt;", "")
        .replace("&quot;", "")
        .replace("&apos;", "")
        .replace(Regex("[ ]{2,}"), " ")
