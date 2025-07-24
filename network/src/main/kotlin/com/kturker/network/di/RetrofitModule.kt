package com.kturker.network.di

import com.google.gson.Gson
import com.kturker.contract.BaseUrl
import com.kturker.network.TimeoutType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(TimeoutType.DEFAULT_CON_TIMEOUT.timeout, TimeUnit.SECONDS)
            .readTimeout(TimeoutType.DEFAULT_TIMEOUT.timeout, TimeUnit.SECONDS)
            .writeTimeout(TimeoutType.DEFAULT_TIMEOUT.timeout, TimeUnit.SECONDS)
            .followSslRedirects(true)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

}
