package tokyo.tommy_kw.musical.redux.core

interface StoreSubscriber<StateType> {
    fun create(state: StoreType)
}