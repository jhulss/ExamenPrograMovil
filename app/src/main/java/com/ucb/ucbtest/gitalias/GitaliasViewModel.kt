package com.ucb.ucbtest.gitalias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Gitalias
import com.ucb.usecases.FindGitAlias
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GitaliasViewModel @Inject constructor(
    private val findGitAlias : FindGitAlias
): ViewModel() {

    sealed class GitaliasState {
        object Init: GitaliasState()
        data class Successful(val model: Gitalias): GitaliasState()
        data class Error(val message: String): GitaliasState()
    }
    private val _flow = MutableStateFlow<GitaliasState>(GitaliasState.Init)
    val flow : StateFlow<GitaliasState> = _flow

    fun fetchGitalias(useID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { findGitAlias.invoke(useID) }
            when (result) {
                is NetworkResult.Success -> {
                    _flow.value = GitaliasState.Successful(model = result.data )
                }
                is NetworkResult.Error -> {
                    _flow.value = GitaliasState.Error(result.error)
                }
            }
        }
    }
}