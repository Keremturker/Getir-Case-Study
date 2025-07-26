package com.kturker.feature.cart.presentation.di

import com.kturker.feature.cart.presentation.navigation.CartNavGraphProvider
import com.kturker.navigation.NavGraphProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface CartNavGraphModule {

    @Binds
    @IntoMap
    @StringKey("Cart")
    fun bindsCartNavGraph(impl: CartNavGraphProvider): NavGraphProvider
}
