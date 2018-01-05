package tokyo.tommy_kw.musical.flux

interface Action<out T> {
    val data: T
}