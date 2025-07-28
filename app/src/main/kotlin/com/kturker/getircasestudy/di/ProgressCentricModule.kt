package com.kturker.getircasestudy.di

import com.kturker.core.presentation.progresscentricnotification.ProgressCentricNotificationManager
import com.kturker.getircasestudy.progresscentric.ProgressCentricNotificationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface ProgressCentricModule {

    @ActivityRetainedScoped
    @Binds
    fun bindsProgressCentricNotificationManager(
        impl: ProgressCentricNotificationManagerImpl
    ): ProgressCentricNotificationManager
}
