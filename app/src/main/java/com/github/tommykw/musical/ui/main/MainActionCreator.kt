package com.github.tommykw.musical.ui.main

import android.util.Log
import com.github.tommykw.musical.data.TheaterRepository
import io.reactivex.schedulers.Schedulers

class MainActionCreator {
    private val dispatcher: MainDispatcher = MainDispatcher()
    private val repository: TheaterRepository = TheaterRepository()

    fun fetchEvent() {
        repository.fetchEvent()
            .subscribeOn(Schedulers.io())
            .subscribe({
                dispatcher.dispatch(MainAction.ShowEvent(it))
            }, {
            })
    }
}