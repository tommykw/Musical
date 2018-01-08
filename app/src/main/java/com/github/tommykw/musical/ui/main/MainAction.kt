package com.github.tommykw.musical.ui.main

import com.github.tommykw.musical.data.entity.Event
import com.github.tommykw.musical.flux.Action

sealed class MainAction<out T> : Action<T> {
    class ShowEvents(override val data: List<Event>) : MainAction<List<Event>>()
    class ShowEvent(override val data: Event) : MainAction<Event>()
}