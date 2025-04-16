package com.ucb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM favorite_books")
    fun getAll(): Flow<List<BookEntity>>

    @Query("SELECT * FROM favorite_books WHERE id = :id")
    suspend fun getById(id: String): BookEntity?

    @Insert
    suspend fun insert(book: BookEntity)

    @Delete
    suspend fun delete(book: BookEntity)
}