package com.bowoon.android.domain.dto


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
@kotlinx.serialization.Serializable
data class WeatherInfo(
    @SerialName("base")
    val base: String? = null, // stations
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("cod")
    val cod: Int? = null, // 200
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("dt")
    val dt: Int? = null, // 1655109385
    @SerialName("id")
    val id: Int? = null, // 1835848
    @SerialName("main")
    val main: Main? = null,
    @SerialName("name")
    val name: String? = null, // Seoul
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null, // 32400
    @SerialName("visibility")
    val visibility: Int? = null, // 10000
    @SerialName("weather")
    val weather: List<Weather?>? = null,
    @SerialName("wind")
    val wind: Wind? = null
) : Parcelable {
    @Parcelize
    @kotlinx.serialization.Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int? = null // 0
    ) : Parcelable

    @Parcelize
    @kotlinx.serialization.Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double? = null, // 37.5714
        @SerialName("lon")
        val lon: Double? = null // 126.9835
    ) : Parcelable

    @Parcelize
    @kotlinx.serialization.Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double? = null, // 25.02
        @SerialName("humidity")
        val humidity: Int? = null, // 54
        @SerialName("pressure")
        val pressure: Int? = null, // 1007
        @SerialName("temp")
        val temp: Double? = null, // 25.05
        @SerialName("temp_max")
        val tempMax: Double? = null, // 26.82
        @SerialName("temp_min")
        val tempMin: Double? = null // 23.71
    ) : Parcelable {
        fun getTemperature(): String = String.format("%.2f°C", temp)
        fun getMaxTemperature(): String = String.format("최고 온도 : %.2f°C", tempMax)
        fun getMinTemperature(): String = String.format("최저 온도 : %.2f°C", tempMin)
    }

    @Parcelize
    @kotlinx.serialization.Serializable
    data class Sys(
        @SerialName("country")
        val country: String? = null, // KR
        @SerialName("id")
        val id: Int? = null, // 8105
        @SerialName("sunrise")
        val sunrise: Int? = null, // 1655064618
        @SerialName("sunset")
        val sunset: Int? = null, // 1655117635
        @SerialName("type")
        val type: Int? = null // 1
    ) : Parcelable

    @Parcelize
    @kotlinx.serialization.Serializable
    data class Weather(
        @SerialName("description")
        val description: String? = null, // clear sky
        @SerialName("icon")
        val icon: String? = null, // 01d
        @SerialName("id")
        val id: Int? = null, // 800
        @SerialName("main")
        val main: String? = null // Clear
    ) : Parcelable {
        fun getWeather(): String = "$main($description)"
        fun getIconUrl(): String = "https://openweathermap.org/img/wn/$icon.png"
    }

    @Parcelize
    @kotlinx.serialization.Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int? = null, // 20
        @SerialName("speed")
        val speed: Double? = null, // 4.12
        @SerialName("gust")
        val gust: Double? = null
    ) : Parcelable {
        fun getWindSpeed(): String = String.format("풍속 : %.2f°C", speed)
        fun getWindGust(): String = String.format("풍속 : %.2f°C", gust)
    }
}