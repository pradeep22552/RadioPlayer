package com.example.radioplayer.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayer.model.Nowplaying
import com.example.radioplayer.repository.SongRepository
import com.example.radioplayer.utils.Resultres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


class PlayingViewModel @ViewModelInject constructor(private val songRepository: SongRepository) :
    ViewModel() {

    private val songsList = MutableLiveData<Resultres<List<Nowplaying>>>()
    val songs = songsList

    init {
        fetchSongs()
    }



    private fun fetchSongs() {
        viewModelScope.launch(){
            songRepository.fetchsongs().collect {
                songsList.value = it
            }
        }
    }
}

