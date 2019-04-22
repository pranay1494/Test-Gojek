package com.example.go_jektest.di.module

import com.example.go_jektest.BuildConfig
import com.example.go_jektest.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, callAdapter: CallAdapter.Factory, converterFactory: Converter.Factory, @Named("baseurl")baseUrl : String ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .build()
    }


    @Singleton
    @Provides
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    internal fun provideRxJavaCallAdapter() : CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    internal fun provideConvertorFactory() : Converter.Factory = GsonConverterFactory.create()

    @Named("baseurl")
    @Singleton
    @Provides
    internal fun provideBaseUrl() = BuildConfig.HOST

    @Provides
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}