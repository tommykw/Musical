package com.github.tommykw.musical.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.tommykw.musical.R
import com.github.tommykw.musical.data.entity.Event
import com.github.tommykw.musical.databinding.MainEventItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    val items = arrayListOf<Event>()
    val onItemClicked: ((item: Event) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.main_event_item, parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.event = item
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(item)
        }
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: MainEventItemBinding) : RecyclerView.ViewHolder(binding.root)
}