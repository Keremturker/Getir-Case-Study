package com.kturker.language

import com.kturker.language.resources.LanguageType

internal data class LanguageUiModel(
    val language: String = LanguageType.TR.name,
    val resources: StringResourcesUiModel = StringResourcesUiModel()
)
