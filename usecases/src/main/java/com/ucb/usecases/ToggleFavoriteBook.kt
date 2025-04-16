package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book
import javax.inject.Inject

class ToggleFavoriteBook @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke(book: Book) {
        if (repository.isFavorite(book)) {
            repository.removeFavorite(book)
        } else {
            repository.addFavorite(book)
        }
    }
}