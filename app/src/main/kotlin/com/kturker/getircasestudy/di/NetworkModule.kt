package com.kturker.getircasestudy.di

import com.kturker.contract.BaseUrl
import com.kturker.getircasestudy.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
