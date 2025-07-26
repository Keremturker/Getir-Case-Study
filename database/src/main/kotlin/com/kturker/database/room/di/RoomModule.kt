package com.kturker.database.room.di

import android.content.Context
import androidx.room.Room
import com.kturker.database.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME_NB = "getir_case_study"

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME_NB)
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideProductDao(db: AppDatabase) = db.productDao()

    @Provides
    @Singleton
    fun provideSuggestedProductDao(db: AppDatabase) = db.suggestedProductDao()

    @Provides
    @Singleton
    fun provideCartDao(db: AppDatabase) = db.cardDao()

}