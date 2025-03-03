package com.ucb.ucbtest.gitalias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Gitalias
import com.ucb.usecases.FindGitAlias
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitaliasViewModel @Inject constructor(
    val findGitAlias : FindGitAlias
): ViewModel() {

    sealed class GitaliasState {
        object Init: GitaliasState()
        class Successful(val model: Gitalias): GitaliasState()
    }
    private val _flow = MutableStateFlow<GitaliasState>(GitaliasState.Init)
    val flow : StateFlow<GitaliasState> = _flow

    fun fetchGitalias(useID: String) {
        viewModelScope.launch {
           _flow.value = GitaliasState.Successful(model = findGitAlias.invoke(useID))
        }
    }
}