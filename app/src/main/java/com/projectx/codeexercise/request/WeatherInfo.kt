package com.projectx.codeexercise.request

import com.google.gson.annotations.SerializedName

class WeatherInfo {
    /**
     * coord : {"lon":114.16,"lat":22.29}
     * weather : [{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}]
     * base : stations
     * main : {"temp":302.54,"feels_like":304.4,"temp_min":301.48,"temp_max":303.15,"pressure":1010,"humidity":70}
     * visibility : 10000
     * wind : {"speed":5.1,"deg":70}
     * clouds : {"all":40}
     * dt : 1599904978
     * sys : {"type":1,"id":9154,"country":"HK","sunrise":1599862165,"sunset":1599906610}
     * timezone : 28800
     * id : 1819729
     * name : Hong Kong
     * cod : 200
     */
    @SerializedName("coord")
    var coord: Coord? = null

    @SerializedName("base")
    var base: String? = null

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("visibility")
    var visibility = 0

    @SerializedName("wind")
    var wind: Wind? = null

    @SerializedName("clouds")
    var clouds: Clouds? = null

    @SerializedName("dt")
    var dt = 0

    @SerializedName("sys")
    var sys: Sys? = null

    @SerializedName("timezone")
    var timezone = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("cod")
    var cod = 0

    @SerializedName("weather")
    var weather: List<Weather>? = null

    class Coord {
        /**
         * lon : 114.16
         * lat : 22.29
         */
        @SerializedName("lon")
        var lon = 0.0

        @SerializedName("lat")
        var lat = 0.0
    }

    class Main {
        /**
         * temp : 302.54
         * feels_like : 304.4
         * temp_min : 301.48
         * temp_max : 303.15
         * pressure : 1010
         * humidity : 70
         */
        @SerializedName("temp")
        var temp = 0.0

        @SerializedName("feels_like")
        var feelsLike = 0.0

        @SerializedName("temp_min")
        var tempMin = 0.0

        @SerializedName("temp_max")
        var tempMax = 0.0

        @SerializedName("pressure")
        var pressure = 0

        @SerializedName("humidity")
        var humidity = 0
    }

    class Wind {
        /**
         * speed : 5.1
         * deg : 70
         */
        @SerializedName("speed")
        var speed = 0.0

        @SerializedName("deg")
        var deg = 0
    }

    class Clouds {
        /**
         * all : 40
         */
        @SerializedName("all")
        var all = 0
    }

    class Sys {
        /**
         * type : 1
         * id : 9154
         * country : HK
         * sunrise : 1599862165
         * sunset : 1599906610
         */
        @SerializedName("type")
        var type = 0

        @SerializedName("id")
        var id = 0

        @SerializedName("country")
        var country: String? = null

        @SerializedName("sunrise")
        var sunrise = 0

        @SerializedName("sunset")
        var sunset = 0
    }

    class Weather {
        /**
         * id : 802
         * main : Clouds
         * description : scattered clouds
         * icon : 03d
         */
        @SerializedName("id")
        var id = 0

        @SerializedName("main")
        var main: String? = null

        @SerializedName("description")
        var description: String? = null

        @SerializedName("icon")
        var icon: String? = null
    }
}