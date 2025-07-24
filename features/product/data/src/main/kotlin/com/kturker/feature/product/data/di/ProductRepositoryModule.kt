package com.kturker.feature.product.data.di

import com.kturker.feature.product.data.ProductRepositoryImpl
import com.kturker.feature.product.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface ProductRepositoryModule {

    @Binds
    fun bindRepository(repository: ProductRepositoryImpl): ProductRepository
}
