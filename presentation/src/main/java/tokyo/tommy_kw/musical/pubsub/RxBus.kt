package tokyo.tommy_kw.musical.pubsub

import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

/**
 * Created by tommy on 2016/06/30.
 */
class RxBus {
    private val bus = SerializedSubject(PublishSubject.create<Any>())

    fun send(o: Any) = bus.onNext(o)
    fun toObserverable(): rx.Observable<Any> = bus
    fun hasObservers() = bus.hasObservers()
}