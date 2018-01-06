package com.github.tommykw.musical.ui.main

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import com.github.tommykw.musical.flux.Dispatcher

class MainDispatcher : Dispatcher() {
    private val dispatcherShowEvent: FlowableProcessor<MainAction.ShowEvent>
        = BehaviorProcessor.create<MainAction.ShowEvent>().toSerialized()
    val onShowEventRepository: Flowable<MainAction.ShowEvent> = dispatcherShowEvent

    fun dispatch(action: MainAction.ShowEvent) {
        dispatcherShowEvent.onNext(action)
    }
}