package com.github.tommykw.musical.ui.musical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.tommykw.musical.data.entity.Musical
import com.github.tommykw.musical.databinding.ListItemMusicalBinding

class MusicalAdapter : ListAdapter<Musical, RecyclerView.ViewHolder>(MusicalDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val musical = getItem(position)
        (holder as MusicalViewHolder).bind(musical)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MusicalViewHolder(
                ListItemMusicalBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }

    class MusicalViewHolder(
            private val binding: ListItemMusicalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Musical) {
            binding.apply {
                musical = item
                executePendingBindings()
            }
        }
    }
}

private class MusicalDiffCallback : DiffUtil.ItemCallback<Musical>() {

    override fun areItemsTheSame(oldItem: Musical, newItem: Musical): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Musical, newItem: Musical): Boolean {
        return oldItem == newItem
    }
}