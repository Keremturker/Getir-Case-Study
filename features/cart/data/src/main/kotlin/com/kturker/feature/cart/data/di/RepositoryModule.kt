package com.kturker.feature.cart.data.di

import com.kturker.core.domain.CartRepository
import com.kturker.feature.cart.data.repository.CartRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindCartRepository(repository: CartRepositoryImpl): CartRepository
}
