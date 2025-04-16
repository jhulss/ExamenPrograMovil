package com.ucb.data

import com.ucb.data.local.BookDao
import com.ucb.data.remote.BookRemoteDataSource
import com.ucb.domain.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val bookDao: BookDao
) {
    suspend fun searchBooks(query: String): List<Book> {
        return remoteDataSource.searchBooks(query)
    }

    fun getFavorites(): Flow<List<Book>> = bookDao.getAll()

    suspend fun addFavorite(book: Book) {
        bookDao.insert(book)
    }

    suspend fun removeFavorite(book: Book) {
        bookDao.delete(book)
    }

    suspend fun isFavorite(book: Book): Boolean {
        return bookDao.getById(book.id) != null
    }
}