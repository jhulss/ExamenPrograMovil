package com.ucb.ucbtest.books

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucb.domain.Book

@Composable
fun BooksScreen(
    navController: NavController,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { viewModel.searchBooks(searchQuery) }
        )

        when {
            state.isLoading -> LoadingIndicator()
            state.error != null -> ErrorMessage(state.error!!)
            state.books.isNotEmpty() -> BookList(
                books = state.books,
                onBookClick = { /* Navigate to details */ },
                onLikeClick = { book -> viewModel.toggleFavorite(book) }
            )
            else -> EmptyState()
        }
    }
}

@Composable
fun BookListItem(
    book: Book,
    isFavorite: Boolean,
    onBookClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)

                            IconButton(onClick = onLikeClick) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
            }

            Text("Autores: ${book.authors.joinToString(", ")}")
            Text("Año: ${book.publicationYear}")
        }
    }
}