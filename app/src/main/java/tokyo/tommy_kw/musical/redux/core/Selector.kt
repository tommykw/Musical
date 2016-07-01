package tokyo.tommy_kw.musical.redux.core

/**
 * Created by tommy on 2016/07/01.
 */
interface Selector<State : StoreType> {
    fun call(state: State)
}