package tokyo.tommy_kw.musical.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.android.schedulers.AndroidSchedulers
import tokyo.tommy_kw.musical.data.entity.Event

class MainStore {
    private val dispatcher: MainDispatcher = MainDispatcher()

    val repos: LiveData<List<Event>>
        get() {
            val publisher = dispatcher.onShowEventRepository
                .map { it.data }
                .observeOn(AndroidSchedulers.mainThread())

            return LiveDataReactiveStreams.fromPublisher(publisher)
        }
}