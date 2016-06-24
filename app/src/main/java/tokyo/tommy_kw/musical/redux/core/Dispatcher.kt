package tokyo.tommy_kw.musical.redux.core

/**
 * Created by tommy on 2016/06/24.
 */
interface Dispatcher {
    fun call(action: Action): Any
}