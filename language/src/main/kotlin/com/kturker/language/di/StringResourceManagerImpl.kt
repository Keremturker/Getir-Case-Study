package com.kturker.language.di

import com.kturker.language.LanguageUiModel
import com.kturker.language.StringResourceManager
import com.kturker.language.StringResourcesUiModel
import com.kturker.language.resources.resourceEN
import com.kturker.language.resources.stringResources
import java.util.Locale
import javax.inject.Inject
import kotlin.reflect.KProperty1

internal class StringResourceManagerImpl @Inject constructor() : StringResourceManager {

    private var currentResource = LanguageUiModel()

    override fun setLanguage(code: String) {
        currentResource = stringResources.find { it.language == Locale.getDefault().language }
            ?: resourceEN
    }

    override fun get(property: KProperty1<StringResourcesUiModel, String>): String {
        return property.get(currentResource.resources)
    }

    override fun get(
        property: KProperty1<StringResourcesUiModel, String>,
        vararg params: String
    ): String {
        var resource = property.get(currentResource.resources)
        params.forEachIndexed { index, param ->
            resource = resource.replace("{$index}", param)
        }
        return resource
    }
}
