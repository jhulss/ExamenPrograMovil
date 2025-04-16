package com.ucb.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucb.domain.Book

@Entity(tableName = "favorite_books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String,
    val publicationYear: Int
)

fun Book.toEntity() = BookEntity(
    id = "$title-${authors.joinToString(",")}",
    title = title,
    authors = authors.joinToString(","),
    publicationYear = publicationYear
)

fun BookEntity.toDomain() = Book(
    title = title,
    authors = authors.split(","),
    publicationYear = publicationYear
)