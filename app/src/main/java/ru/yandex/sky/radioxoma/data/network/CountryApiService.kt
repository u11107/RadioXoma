package ru.yandex.sky.radioxoma.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApiService {
    @GET("countres")
    suspend fun getCountres(
        @Query("hidebroken") hidebroken: Boolean = true
    ): List<CountryResponse>
}