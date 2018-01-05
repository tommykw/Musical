package tokyo.tommy_kw.musical.ui.main

import tokyo.tommy_kw.musical.data.entity.Event
import tokyo.tommy_kw.musical.flux.Action

sealed class MainAction<out T> : Action<T> {
    class ShowEvent(override val data: List<Event>) : MainAction<List<Event>>()
}