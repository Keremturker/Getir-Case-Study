package com.kturker.language

import kotlin.reflect.KProperty1

interface StringResourceManager {

    fun setLanguage(code: String)

    operator fun get(property: KProperty1<StringResourcesUiModel, String>): String

    operator fun get(
        property: KProperty1<StringResourcesUiModel, String>,
        vararg params: String
    ): String
}
