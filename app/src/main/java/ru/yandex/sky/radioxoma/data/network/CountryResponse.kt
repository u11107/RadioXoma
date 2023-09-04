package ru.yandex.sky.radioxoma.data.network

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("iso_3166_1")
    val countryCode: String? = null,
    @SerializedName("count")
    val stationCountry: String? = null,
)
