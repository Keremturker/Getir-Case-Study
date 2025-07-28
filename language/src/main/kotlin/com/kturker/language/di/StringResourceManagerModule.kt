package com.kturker.language.di

import com.kturker.language.StringResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface StringResourceManagerModule {

    @Binds
    fun bindsStringResourceManager(impl: StringResourceManagerImpl): StringResourceManager
}
