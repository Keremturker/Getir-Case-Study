package com.kturker.language.di

import com.kturker.language.StringResourceManager
import com.kturker.language.StringResourcesUiModel
import com.kturker.language.resources.resourceTR
import javax.inject.Inject
import kotlin.reflect.KProperty1

internal class StringResourceManagerImpl @Inject constructor() : StringResourceManager {

    override fun get(property: KProperty1<StringResourcesUiModel, String>): String {
        return property.get(resourceTR.resources)
    }
}