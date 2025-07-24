package com.kturker.language

import com.kturker.language.resources.Language

data class LanguageUiModel(
    val language: Language = Language.TR,
    val resources: StringResourcesUiModel = StringResourcesUiModel()
)