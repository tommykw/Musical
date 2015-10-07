package tokyo.tommy_kw.kotlinsample.entity

/**
 * Created by tommy on 15/10/07.
 */
class Weather(base:String, visibility:Int, dt:Int,
              id:Int, name:String, cod:Int) {

    public val base = base
    public val visibility = visibility
    public val dt = dt
    public val id = id
    public val name = name
    public val cod = cod
}