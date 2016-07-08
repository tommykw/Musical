package tokyo.tommy_kw.musical.adapter

import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * [WIP]
 * Created by tommy on 2016/07/04.
 */
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

    class Builder<T : Any> (val list:List<T>, val variable: Int) {
        private val map: MutableMap<Class<*>, Int> = mutableMapOf()
        private var onClick: View.OnClickListener? = null
        private var onLongClick: View.OnLongClickListener? = null

        fun layoutHandler(layoutHandler: LayoutHandler) = apply {

        }
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

    interface LayoutHandler {
        @LayoutRes fun getItemLayout(item: Any, position: Int): Int
    }
}