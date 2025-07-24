package com.kturker.language.di

import com.kturker.language.StringResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface StringResourceManagerModule {

    @ViewModelScoped
    @Binds
    fun bindsStringResourceManager(impl: StringResourceManagerImpl): StringResourceManager
}
