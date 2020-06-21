package com.github.tommykw.musical.ui.musical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tommykw.musical.data.entity.Musical
import com.github.tommykw.musical.data.repository.MusicalRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MusicalsViewModel @Inject constructor(
        private val musicalRepository: MusicalRepository
) : ViewModel() {

    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _snackbar = MutableLiveData<String?>()

    private val _spinner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spinner

    private val trilogyChannel = ConflatedBroadcastChannel<Int>()

    val musicalsUsingFlow: Flow<List<Musical>> = trilogyChannel.asFlow()
        .flatMapLatest { trilogy ->
            _spinner.value = true
            musicalRepository.musicalFlow
//            if (trilogy == NoTrilogy) {
//                musicalRepository.musicalFlow
//            } else {
//                musicalRepository.getMusicalsForTrilogyFlow(trilogy)
//            }
        }.onEach {
            _spinner.value = false
        }.catch { throwable ->
            _snackbar.value = throwable.message
        }

    init {
        //clearTrilogyNumber()

        loadData {
            musicalRepository.tryUpdateRecentMusicalsCache()
        }
    }

//    fun setTrilogyNumber(num: Int) {
//        trilogyChannel.offer(Trilogy(num))
//        loadData { musicalRepository.tryUpdateRecentMusicalForTrilogyCache(Trilogy(num)) }
//    }
//
//    private fun clearTrilogyNumber() {
//        trilogyChannel.offer(NoTrilogy)
//        loadData { musicalRepository.tryUpdateRecentMusicalsCache() }
//    }

    fun onSnackbarShown() {
        _snackbar.value = null
    }

    private fun loadData(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}