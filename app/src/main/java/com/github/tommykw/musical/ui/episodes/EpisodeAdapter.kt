package com.github.tommykw.musical.ui.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.tommykw.musical.data.entity.Episode
import com.github.tommykw.musical.databinding.ListItemEpisodeBinding

class EpisodeAdapter : ListAdapter<Episode, RecyclerView.ViewHolder>(EpisodeDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)
        (holder as EpisodeViewHolder).bind(episode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EpisodeViewHolder(
                ListItemEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }

    class EpisodeViewHolder(
            private val binding: ListItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Episode) {
            binding.apply {
                episode = item
                executePendingBindings()
            }
        }
    }
}

private class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {

    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.episodeId == newItem.episodeId
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}