package com.kturker.language.resources

internal enum class LanguageType(val code: String) {
    EN(code = "en"),
    TR(code = "tr"),
    ES(code = "es"),
    IT(code = "it"),
    FR(code = "fr")
}

internal var stringResources = listOf(resourceEN, resourceTR, resourceES, resourceFR, resourceIT)
