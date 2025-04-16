package com.ucb.ucbtest.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Book
import com.ucb.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val searchBooks: SearchBooks,
    private val getFavorites: GetFavoriteBooks,
    private val toggleFavorite: ToggleFavoriteBook
) : ViewModel() {

    private val _state = MutableStateFlow(BooksViewState())
    val state: StateFlow<BooksViewState> = _state.asStateFlow()

    init {
        loadFavorites()
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            searchBooks(query).collect { result ->
                result.onSuccess { books ->
                    _state.update { state ->
                        state.copy(
                            books = books,
                            isLoading = false
                        )
                    }
                }.onFailure { error ->
                    _state.update { state ->
                        state.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            toggleFavorite(book)
            loadFavorites()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            getFavorites().collect { favorites ->
                _state.update { it.copy(favoriteBooks = favorites) }
            }
        }
    }
}

data class BooksViewState(
    val books: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)