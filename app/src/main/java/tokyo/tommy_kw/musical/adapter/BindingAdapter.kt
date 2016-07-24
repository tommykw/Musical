package tokyo.tommy_kw.musical.adapter

import android.content.DialogInterface
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.os.Looper
import android.support.annotation.Keep
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference

/**
 * [WIP]
 * Created by tommy on 2016/07/04.
 */
@Keep
class BindingAdapter<T : Any>(): RecyclerView.Adapter<BindingAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        throw UnsupportedOperationException()
    }

    @Keep
    class Builder<T : Any> (val list:List<T>, val variable: Int) {
        private val map: MutableMap<Class<*>, Int> = mutableMapOf()
        private var onClick: View.OnClickListener? = null
        private var onLongClick: View.OnLongClickListener? = null
        private var handler: LayoutHandler? = null

        fun map(clazz: Class<*>, @LayoutRes layout: Int) = apply { map.put(clazz, layout) }
        inline fun <reified T: Any> map(@LayoutRes layout: Int) = map(T::class.java, layout)

        fun layoutHandler(layoutHandler: LayoutHandler) = apply {

        }

        fun onBindListener(listener: OnBindListener) = apply {}

//        fun onBind() = apply {
//            onBindListener
//        }
    }
    
    class ItemPosition(val item: Any, val position: Int)

    class ItemViewPosition(val item: Any, val view: View, val position: Int)

    interface LayoutHandler {
        @LayoutRes fun getItemLayout(item: Any, view: View, position: Int)
    }

    interface OnBindListener {
        fun onBind(item: Any, view: View, position: Int)
    }

    interface OnClickListener {
        fun onClick(item: Any, view :View, position: Int)
    }

    interface OnLongClickListener {
        fun onLongClick(item: Any, view: View, position: Int)
    }

    class ViewHolder(val binding: ViewDataBinding,
                     val variable: Int): RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: Any,
                   pos: Int,
                   //onBind: OnBindListener?,
                   onClick: View.OnClickListener?,
                   onLongClick: View.OnLongClickListener
                   ) {

        }
    }

    private fun isForDataBinding(payloads: List<Any>?): Boolean {
        if (payloads == null || payloads.size == 0) return false
        payloads.forEach { if (it == DATA_INVALIDATION) return false }
        return true
    }

    private val DATA_INVALIDATION = Any()

    private class WeakReferenceOnListChangedCallback<T : Any>(private val adapter: BindingAdapter<T>): ObservableList.OnListChangedCallback<ObservableList<T>>() {
        override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            return getAdapter().notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            throw UnsupportedOperationException()
        }

        override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            throw UnsupportedOperationException()
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            throw UnsupportedOperationException()
        }

        private val adapterRef = WeakReference<BindingAdapter<T>>(adapter)
        private fun getAdapter(): BindingAdapter<T> {
            if (Thread.currentThread() != Looper.getMainLooper().thread) {
                throw IllegalStateException("you must modify the ObservableList on io thread")
            }
            return adapterRef.get()
        }

        override fun onChanged(list: ObservableList<T>) =
                getAdapter().notifyDataSetChanged()

    }
}