package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchBooks @Inject constructor(private val repository: BookRepository) {
    operator fun invoke(query: String): Flow<Result<List<Book>>> = flow {
        try {
            val result = repository.searchBooks(query)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}