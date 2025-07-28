package com.kturker.core.presentation

import com.kturker.language.StringResourceManager
import com.kturker.language.StringResourcesUiModel
import kotlin.reflect.KProperty1

object PreviewStringResourceManager : StringResourceManager {
    override fun setLanguage(code: String) = Unit

    override fun get(property: KProperty1<StringResourcesUiModel, String>): String = property.name

    override fun get(
        property: KProperty1<StringResourcesUiModel, String>,
        vararg params: String
    ): String {
        return "${property.name}${params.joinToString("")}"
    }
}
