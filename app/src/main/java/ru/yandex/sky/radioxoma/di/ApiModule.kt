package ru.yandex.sky.radioxoma.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yandex.sky.radioxoma.constant.BASE_URL
import ru.yandex.sky.radioxoma.data.network.CountryApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    //для логирования запросов и ответов с сервера
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    //используется для выполнения запросов к серверу. Он добавляет логгер
    // из первой функции в качестве интерсептора (перехватчика)
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    //создает экземпляр Retrofit, который используется для
    // создания экземпляров CountryApiService. Он использует OkHttpClient из
    // второй функции для выполнения запросов и преобразует ответы сервера в объекты Kotlin с
    // помощью GsonConverterFactory
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    //предоставляет экземпляр CountryApiService, созданный с помощью Retrofit из третьей функции
    fun provideNewsApi(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }
}