package com.kturker.feature.product.presentation.di

import com.kturker.feature.product.presentation.navigation.ProductNavGraphProvider
import com.kturker.navigation.NavGraphProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface ProductNavGraphModule {

    @Binds
    @IntoMap
    @StringKey("Product")
    fun bindsProductNavGraph(impl: ProductNavGraphProvider): NavGraphProvider
}
