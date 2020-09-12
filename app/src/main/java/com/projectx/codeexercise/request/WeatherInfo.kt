package com.projectx.codeexercise.request

import com.google.gson.annotations.SerializedName

class WeatherInfo {
    /**
     * coord : {"lon":139,"lat":35}
     * weather : [{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}]
     * base : stations
     * main : {"temp":281.52,"feels_like":278.99,"temp_min":280.15,"temp_max":283.71,"pressure":1016,"humidity":93}
     * wind : {"speed":0.47,"deg":107.538}
     * clouds : {"all":2}
     * dt : 1560350192
     * sys : {"type":3,"id":2019346,"message":0.0065,"country":"JP","sunrise":1560281377,"sunset":1560333478}
     * timezone : 32400
     * id : 1851632
     * name : Shuzenji
     * cod : 200
     */
    @SerializedName("coord")
    private var coord: Coord? = null

    @SerializedName("base")
    private var base: String? = null

    @SerializedName("main")
    private var main: Main? = null

    @SerializedName("wind")
    private var wind: Wind? = null

    @SerializedName("clouds")
    private var clouds: Clouds? = null

    @SerializedName("dt")
    private var dt = 0

    @SerializedName("sys")
    private var sys: Sys? = null

    @SerializedName("timezone")
    private var timezone = 0

    @SerializedName("id")
    private var id = 0

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("cod")
    private var cod = 0

    @SerializedName("weather")
    private var weather: List<Weather?>? = null

    fun getCoord(): Coord? {
        return coord
    }

    fun setCoord(coord: Coord?) {
        this.coord = coord
    }

    fun getBase(): String? {
        return base
    }

    fun setBase(base: String?) {
        this.base = base
    }

    fun getMain(): Main? {
        return main
    }

    fun setMain(main: Main?) {
        this.main = main
    }

    fun getWind(): Wind? {
        return wind
    }

    fun setWind(wind: Wind?) {
        this.wind = wind
    }

    fun getClouds(): Clouds? {
        return clouds
    }

    fun setClouds(clouds: Clouds?) {
        this.clouds = clouds
    }

    fun getDt(): Int {
        return dt
    }

    fun setDt(dt: Int) {
        this.dt = dt
    }

    fun getSys(): Sys? {
        return sys
    }

    fun setSys(sys: Sys?) {
        this.sys = sys
    }

    fun getTimezone(): Int {
        return timezone
    }

    fun setTimezone(timezone: Int) {
        this.timezone = timezone
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCod(): Int {
        return cod
    }

    fun setCod(cod: Int) {
        this.cod = cod
    }

    fun getWeather(): List<Weather?>? {
        return weather
    }

    fun setWeather(weather: List<Weather?>?) {
        this.weather = weather
    }
}

class Coord {
    /**
     * lon : 139
     * lat : 35
     */
    @SerializedName("lon")
    var lon = 0

    @SerializedName("lat")
    var lat = 0

}

class Main {
    /**
     * temp : 281.52
     * feels_like : 278.99
     * temp_min : 280.15
     * temp_max : 283.71
     * pressure : 1016
     * humidity : 93
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
     * speed : 0.47
     * deg : 107.538
     */
    @SerializedName("speed")
    var speed = 0.0

    @SerializedName("deg")
    var deg = 0.0

}

class Clouds {
    /**
     * all : 2
     */
    @SerializedName("all")
    var all = 0

}

class Sys {
    /**
     * type : 3
     * id : 2019346
     * message : 0.0065
     * country : JP
     * sunrise : 1560281377
     * sunset : 1560333478
     */
    @SerializedName("type")
    var type = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("message")
    var message = 0.0

    @SerializedName("country")
    var country: String? = null

    @SerializedName("sunrise")
    var sunrise = 0

    @SerializedName("sunset")
    var sunset = 0

}

class Weather {
    /**
     * id : 800
     * main : Clear
     * description : clear sky
     * icon : 01n
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