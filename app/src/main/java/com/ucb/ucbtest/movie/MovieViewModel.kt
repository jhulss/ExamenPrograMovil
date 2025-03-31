package com.ucb.ucbtest.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Movie
import com.ucb.usecases.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (private val getPopularMovies: GetPopularMovies): ViewModel() {

    sealed class MovieUIState {
        object Loading: MovieUIState()
        class Loaded(val list: List<Movie>): MovieUIState()
    }
    private val _state = MutableStateFlow<MovieUIState>(MovieUIState.Loading)
    val state : StateFlow<MovieUIState> = _state

    fun loadMovies() {
        _state.value = MovieUIState.Loading
        viewModelScope.launch {
            _state.value = MovieUIState.Loaded(getPopularMovies.invoke())
        }

    }
}