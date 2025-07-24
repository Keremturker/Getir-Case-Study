package com.kturker.feature.product.data.di

import com.kturker.feature.product.data.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
internal object ProductModule {

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService = retrofit.create()
}
