package com.kturker.language

import kotlin.reflect.KProperty1

interface StringResourceManager {

    operator fun get(property: KProperty1<StringResourcesUiModel, String>): String
}