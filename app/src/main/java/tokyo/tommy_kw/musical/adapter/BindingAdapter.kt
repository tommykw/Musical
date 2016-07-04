package tokyo.tommy_kw.musical.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
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

    class ViewHolder(val binding: ViewDataBinding,
                     val variable: Int): RecyclerView.ViewHolder(binding.root) {


    }
}