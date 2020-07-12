package com.github.tommykw.musical.ui.musical

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.tommykw.musical.R
import com.github.tommykw.musical.databinding.MusicalsFragmentBinding
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

class MusicalsFragment : Fragment(), Injectable, CoroutineScope {

    private var job = Job()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MusicalsViewModel

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)

        val binding = MusicalsFragmentBinding.inflate(inflater, container, false)
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

        val adapter = MusicalAdapter()
        binding.musicalList.adapter = adapter

        launch {
            subscribeUi(adapter)
        }

        setHasOptionsMenu(true)
        filterData(-1)
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
            R.id.filter_a -> {
                filterData(1)
                true
            }
            R.id.filter_b -> {
                filterData(2)
                true
            }
            R.id.filter_c -> {
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

    private suspend fun subscribeUi(adapter: MusicalAdapter) {
        viewModel.musicalsUsingFlow.collect { musicals ->
            adapter.submitList(musicals)
        }
    }
}