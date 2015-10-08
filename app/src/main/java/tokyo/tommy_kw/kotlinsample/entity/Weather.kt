package tokyo.tommy_kw.kotlinsample.entity

/**
 * Created by tommy on 15/10/07.
 */
class Weather(coord: Weather.Coord,
              weathers: Array<Weather.WeatherInfo>,
              base:String,
              main: Weather.Main,
              visibility:Int,
              wind: Weather.Wind,
              clouds: Weather.Clouds,
              dt:Int,
              sys: Weather.Sys,
              id:Int,
              name:String,
              cod:Int) {

    class Coord(lon:Float, lat:Float) {
        public val lon = lon
        public val lat = lat
    }

    class WeatherInfo(id:Int, main:String, description:String, icon:String) {
        public val id = id
        public val main = main
        public val description = description
        public val icon = icon
    }

    class Main(temp:Float, pressure:Int, humidity:Int, tempMin:Float, tempMax:Float) {
        public val temp = temp
        public val pressure = pressure
        public val humidity = humidity
        public val tempMin = tempMin
        public val tempMax = tempMax
    }

    class Wind(speed:Float, deg:Int, gust:Float) {
        public val speed = speed
        public val deg = deg
        public val gust = gust
    }

    class Clouds(all:Int) {
        public val all = all
    }

    class Sys(type:Int, id:Int, message:Float, country:String, sunrise:Int, sunset:Int) {
        public val type = type
        public val id = id
        public val message = message
        public val country = country
        public val sunrise = sunrise
        public val sunset = sunset
    }

    public val coord = coord
    public val weathers = weathers
    public val base = base
    public val main = main
    public val visibility = visibility
    public val wind = wind
    public val clouds = clouds
    public val dt = dt
    public val sys = sys
    public val id = id
    public val name = name
    public val cod = cod
}