package com.github.tommykw.musical.ui.episodes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.tommykw.musical.R
import com.github.tommykw.musical.databinding.EpisodesFragmentBinding
import com.github.tommykw.musical.di.Injectable
import com.github.tommykw.musical.ui.injectViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesFragment : Fragment(), Injectable, CoroutineScope {

    private var job = Job()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: EpisodesViewModel

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)

        val binding = EpisodesFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.spinner.observe(viewLifecycleOwner, Observer<Boolean> { show ->
            binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
        })

        viewModel.snackbar.observe(viewLifecycleOwner, Observer<String?> { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        })

        val adapter = EpisodeAdapter()
        binding.episodeList.adapter = adapter

        launch {
            subscribeUi(adapter)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_prequels -> {
                filterData(1)
                true
            }
            R.id.filter_original -> {
                filterData(2)
                true
            }
            R.id.filter_sequels -> {
                filterData(3)
                true
            }
            R.id.filter_all -> {
                filterData(-1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterData(num: Int) {
        viewModel.setTrilogyNumber(num)
    }

    private suspend fun subscribeUi(adapter: EpisodeAdapter) {
        viewModel.episodesUsingFlow.collect { episodes ->
            adapter.submitList(episodes)
        }
    }
}