package com.github.tommykw.musical.entity

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
        val lon = lon
        val lat = lat
    }

    class WeatherInfo(id:Int, main:String, description:String, icon:String) {
        val id = id
        val main = main
        val description = description
        val icon = icon
    }

    class Main(temp:Float, pressure:Int, humidity:Int, tempMin:Float, tempMax:Float) {
        val temp = temp
        val pressure = pressure
        val humidity = humidity
        val tempMin = tempMin
        val tempMax = tempMax
    }

    class Wind(speed:Float, deg:Int, gust:Float) {
        val speed = speed
        val deg = deg
        val gust = gust
    }

    class Clouds(all:Int) {
        val all = all
    }

    class Sys(type:Int, id:Int, message:Float, country:String, sunrise:Int, sunset:Int) {
        val type = type
        val id = id
        val message = message
        val country = country
        val sunrise = sunrise
        val sunset = sunset
    }

    val coord = coord
    val weathers = weathers
    val base = base
    val main = main
    val visibility = visibility
    val wind = wind
    val clouds = clouds
    val dt = dt
    val sys = sys
    val id = id
    val name = name
    val cod = cod
}