package com.github.tommykw.musical.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.android.schedulers.AndroidSchedulers
import com.github.tommykw.musical.data.entity.Event
import com.github.tommykw.musical.flux.Store

class MainStore : Store() {
    private val dispatcher: MainDispatcher = MainDispatcher()

    val repos: LiveData<Event>
        get() {
            val publisher = dispatcher.onShowEventRepository
                .map { it.data }
                .observeOn(AndroidSchedulers.mainThread())

            return LiveDataReactiveStreams.fromPublisher(publisher)
        }
}