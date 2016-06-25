package tokyo.tommy_kw.musical.redux.core

/**
 * Created by tommy on 2016/06/25.
 */
interface DispatchFunction {
    fun call(action: Action): Any
}